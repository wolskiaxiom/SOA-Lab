package pl.agh.soa.ejb.impl;

import pl.agh.soa.ejb.api.MessageManagerInterface;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
public class MessageManager implements MessageManagerInterface {

    @Override
    public void doSomething(){
        System.out.println("I am doing something");
    }

}
