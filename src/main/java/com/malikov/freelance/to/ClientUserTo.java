package com.malikov.freelance.to;

public class ClientUserTo extends BaseUserTo{

    {setRole("client");}

    public ClientUserTo(BaseUserTo baseUserTo) {
        super(baseUserTo);
    }

    public ClientUserTo(){}
}
