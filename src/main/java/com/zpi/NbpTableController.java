package com.zpi;

import com.zpi.data.NbpTableA;
import com.zpi.data.NbpTableB;
import com.zpi.data.NbpTableC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class NbpTableController {

    private NbpCommunication nbpCommunication;

    @RequestMapping(method = RequestMethod.GET,value = "/GET")
    @ResponseBody
    public NbpTableA getNbpTableAEndPoint(){
        nbpCommunication = new NbpCommunication();
        return nbpCommunication.getNbpTableA().get(0);
    }

    public NbpTableB getNbpTableBEndPoint(){
        nbpCommunication = new NbpCommunication();
        return nbpCommunication.getNbpTableB().get(0);
    }

    public NbpTableC getNbpTableCEndPoint(){
        nbpCommunication = new NbpCommunication();
        return nbpCommunication.getNbpTableC().get(0);
    }
}