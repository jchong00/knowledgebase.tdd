package org.platformparm.knowledgebase.tdd.mock;

public class UserRegisterImpl implements UserRegister {

    private String id;
    private String pwd;

    @Override
    public void setPassword(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    public String getPassword() {
        return pwd;
    }


}
