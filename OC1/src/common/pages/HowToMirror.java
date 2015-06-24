package common.pages;

import common.util.AbstractMirror;

public final class HowToMirror
    extends AbstractMirror<HowTo>
{   
    public HowToMirror() {
        super(HowTo.class,"submit_issue");
    }
    
    public Object invoke(String method, Object[] args) {
        if (method.equals("submit_issue")) { return target.submitIssueButton();}
        throw methodNotFound(method, args);
    }

}
