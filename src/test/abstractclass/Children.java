package test.abstractclass;

public class Children extends Parent{

    @Override
    public void fct() {
        System.out.println("我是子类");
    }

    @Override
    public void fct1(){
        super.fct();
    }
}
