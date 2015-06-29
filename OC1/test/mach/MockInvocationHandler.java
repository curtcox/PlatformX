package mach;

import org.junit.ComparisonFailure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static mach.Phase.current;
import static mach.Phase.invoke;

/**
 * Handles the method invocation for a single mock.
 * What that means depends entirely on the current phase.
 */
final class MockInvocationHandler
    implements InvocationHandler
{
    Invocation latest;
    final String name;
    final Class clazz;
    final Map<Invocation,Object> returns = new HashMap();
    final Set<Invocation> nos = new HashSet();
    final Map<Invocation,Object> invoked = new HashMap();
    final MockFactory factory;

    <T> MockInvocationHandler(MockFactory factory, Class clazz, String name) {
        this.factory = factory;
        this.clazz = clazz;
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (toString(method)) { return toString(); }
        if (equals(method))   { return equals(proxy, args); }
        if (hashCode(method)) { return hashCode(); }

        Invocation invocation = new Invocation(proxy,method,args,factory.wildcards);
        factory.handler = this;

        if (current == Phase.no)      { return no(invocation);      }
        if (current == Phase.returns) { return returns(invocation); }
        if (current == Phase.invoke)  { return invoke(invocation);  }
        if (current == Phase.verify)  { return verify(invocation);  }

        throw new UnsupportedOperationException("Invalid phase : " + current);
    }

    private Object no(Invocation invocation) throws Throwable {
        nos.add(invocation);
        current = invoke;
        return dummy(invocation);
    }

    private Object dummy(Invocation invocation) {
        if (invocation.method.getReturnType().equals(boolean.class)) {
            return false;
        }
        return null;
    }

    private Object returns(Invocation invocation) throws Throwable {
        returns.put(invocation,factory.result);
        Object value = factory.result;
        factory.result = null;
        current = invoke;
        factory.wildcards = null;
        return value;
    }

    private Object verify(Invocation expected) throws Throwable {
        if (!invoked.containsKey(expected)) {
            for (Invocation received : invoked.keySet()) {
                if (received.method.equals(expected.method)) {
                    assertEquals(expected, received);
                }
            }
            fail("Missing invocation " + expected);
        }
        return invoked.get(expected);
    }

    private Object invoke(Invocation invocation) throws Throwable {
        if (nos.contains(invocation)) {
            String message = "Unwanted invocation " + invocation;
            fail(message);
        }
        Object result = result(invocation);
        invoked.put(invocation, result);
        latest = invocation;
        return result;
    }

    private Object result(Invocation invocation) throws Throwable {
        if (invocation.returnsVoid()) {
            return null;
        }
        if (returns.containsKey(invocation)) {
            return returns.get(invocation);
        }
        if (thereAreMatchesForMethod(invocation)) {
            Invocation expected = expectedForMethod(invocation.method);
            String message = "!=";
            throw new ComparisonFailure(message, expected.toString(),invocation.toString());
        }
        String message = String.format("[%s] is not defined for [%s]",invocation.method,this);
        throw new UnsupportedOperationException(message);
    }

    private Invocation expectedForMethod(Method method) {
        for (Invocation i : returns.keySet()) {
            if (i.method == method) {
                return i;
            }
        }
        return null;
    }

    private boolean thereAreMatchesForMethod(Invocation invocation) {
        for (Invocation i : returns.keySet()) {
            if (i.method == invocation.method) {
                return true;
            }
        }
        return false;
    }

    private static boolean equals(Method method) {
        return method.getName().equals("equals");
    }

    private static boolean toString(Method method) {
        return method.getName().equals("toString");
    }

    private static boolean hashCode(Method method) {
        return method.getName().equals("hashCode");
    }

    @Override
    public String toString() {
        return name + ":" +clazz + "@" + System.identityHashCode(this);
    }

    private boolean equals(Object proxy, Object[] args) {
        if (args==null || args.length != 1) {
            return false;
        }
        return proxy == args[0];
    }
}
