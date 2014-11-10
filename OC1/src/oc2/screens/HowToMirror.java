package oc2.screens;

import java.util.Arrays;
import oc1.util.Mirror;

public final class HowToMirror
    implements Mirror
{   
    HowTo target;
    
    public void setTarget(Object target) {
        this.target = (HowTo) target;
    }

    public Object invoke(String method, Object[] args) {
        if (method.equals("submit_issue")) { return target.submitIssueButton();}
        throw new IllegalArgumentException(method + Arrays.asList(args));
    }

    public String[] getMethods() {
        return new String[] {"submit_issue"};
    }

}
