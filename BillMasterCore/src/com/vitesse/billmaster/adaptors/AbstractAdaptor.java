/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitesse.billmaster.adaptors;

import com.vitessesoft.generics.Persistable;
import com.vitessesoft.gui.GUIConstants;
import com.vitessesoft.jpa.PersistentManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Sameera Danthanarayana
 */
public abstract class AbstractAdaptor {
    
     public void save(List<Persistable> data)throws Exception{

        EntityManager em = PersistentManager.getEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            for (Persistable p : data) {
                switch (p.$getAction()) {
                    case GUIConstants.ADD_ACTION:
                        em.persist(p);
                        break;
                    case GUIConstants.EDIT_ACTION:
                        em.merge(p);
                        break;
                    case GUIConstants.DELETE_ACTION:
                        em.remove(p);
                        break;
                }
            }
        } finally {
            et.commit();
        }

    }
    
}
