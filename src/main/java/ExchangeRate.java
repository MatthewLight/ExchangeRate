public class ExchangeRate {

    public static void main(String[] args) {
        try{
            UserInterface callUI = new UserInterface();
            callUI.createUI();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
