package com.game;

import com.game.account.Account;
import com.game.account.AccountService;
import com.game.scene.Scene;
import com.game.scene.service.MapService;
import com.game.scene.service.SceneService;

public class MessageHandler {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void handle(){

        String revMsg = null;
        String[] split = this.message.split(" ");

        switch (split[0]) {
            case "login":
                try {
                    String accountId = split[1];
                    String password = split[2];
                    boolean b = SpringContext.getBean("accountService", AccountService.class)
                            .login(accountId, password);
                    if (b)
                        revMsg = "登录成功";
                    else
                        revMsg = "登录失败";
                } catch (Exception e) {
                    revMsg = e.getMessage();
                }
                break;
            case "logout":
                try {
                    String accountIdc = split[1];
                    SpringContext.getBean("accountService", AccountService.class)
                            .logout(accountIdc);
                    revMsg = "登出成功";
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "create-role":
                try {
                    String accountId = split[1];
                    int job = Integer.parseInt(split[2]);
                    int sex = Integer.parseInt(split[3]);
                    boolean b = SpringContext.getBean("accountService", AccountService.class)
                            .createPlayer(accountId, job, sex);
                    if (b)
                        revMsg = "创建玩家成功";
                    else
                        revMsg = "创建玩家失败";
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "enter":
                try {
                    AccountService accountService = SpringContext.getBean("accountService", AccountService.class);
                    Account loginAccount = accountService.getLoginAccount();
                    if (loginAccount == null) {
                        revMsg = "用户没登陆";
                        break;
                    }
                    String accountId = loginAccount.getAccountId();
                    int curMapId = accountService.getCurMap(accountId);
                    SpringContext.getBean("sceneService", SceneService.class).enter(accountId, curMapId);
                    revMsg = "进入场景";
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "move":
                try {
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    Account loginAccount = SpringContext.getBean("accountService", AccountService.class)
                            .getLoginAccount();
                    if (loginAccount == null){
                        revMsg = "用户没登陆";
                        break;
                    }
                    Scene scene = SpringContext.getBean("sceneService",SceneService.class)
                            .getScene(loginAccount.getAccountId());
                    if (scene == null){
                        revMsg = "用户还没进入场景, 请先enter";
                        break;
                    }
                    scene.move(x, y);
                    String msg = scene.getMessage();
                    revMsg = "已移动到指定位置" + "\n" + msg;
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "change-map":
                try {
                    Account loginAccount = SpringContext.getBean("accountService", AccountService.class)
                            .getLoginAccount();
                    if (loginAccount == null){
                        revMsg = "用户没登陆";
                        break;
                    }
                    String accountId = loginAccount.getAccountId();
                    String mapName = split[1];
                    SpringContext.getBean("mapService", MapService.class).changeMap(accountId, mapName);
                    int mapId = SpringContext.getBean("mapService", MapService.class).name2Id(mapName);
                    SpringContext.getBean("sceneService",SceneService.class).enter(accountId, mapId);
                    revMsg = "切换地图成功";
                }catch (Exception e){
                    revMsg = e.getMessage();
                }
                break;
            case "clear":
                revMsg = "clear";
                break;
        }

        setMessage(revMsg);
    }
}
