/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.authentication.beans;

import app.facade.UsersFacade;
import app.model.entity.Users;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author WirtualBox
 */
@Stateless
public class LoginEJB {
    @EJB
    private UsersFacade usersFacade;
    
    @Resource
    private SessionContext sctx;
    
 public String getMyLogin() {
     return sctx.getCallerPrincipal().getName();
 }
 public Users getMyUser() {
     return usersFacade.findUserByLogin(getMyLogin());
 }
}
