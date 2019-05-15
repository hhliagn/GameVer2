package com.game;

public class MessageHandler {

    private CommonService commonService = SpringContext.getBean("commonService");

    public String handle(String msg){

        String revMsg = null;
        String[] split = msg.split(" ");

        switch (split[0]) {
            case "login":
                try {
                    String accountId = split[1];
                    String password = split[2];
                    revMsg = commonService.login(accountId, password);
                } catch (Exception e) {
                    revMsg = e.getMessage();
                }
                break;
            case "create-role":
                try {
                    String accountId = split[1];
                    int job = Integer.parseInt(split[2]);
                    int sex = Integer.parseInt(split[3]);
                    revMsg = commonService.createRole(accountId, job, sex);
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "enter":
                try {
                    revMsg = commonService.enter();
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "move":
                try {
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    revMsg = commonService.move(x, y);
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "change-map":
                try {
                    String mapName = split[1];
                    revMsg = commonService.changeMap(mapName);
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "clear":
                revMsg = "clear";
                break;
        }

        return revMsg;
    }
}
