package ifpb.pod.q12.node3.server;

/**
 * Created by Natarajan Rodrigues on 06/10/16.
 */

public class GlobalVariable {

    private static final GlobalVariable INST_VARIABLE = new GlobalVariable();
    private int _op1 = 0;
    private int _op2 = 0;

    private GlobalVariable(){}

    public void incrOp1(){
        _op1++;
    }

    public void incrOp2(){
        _op2++;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("[");
        buff.append(_op1).append(", ").append(_op2);
        buff.append("]");
        return buff.toString();
    }

    public static GlobalVariable instance(){
        return INST_VARIABLE;
    }

}