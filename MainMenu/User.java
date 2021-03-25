/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import Entities.*;

/**
 *
 * @author ASUS
 */
class User {
        private String Supplier_ID,Supplier_Name,Email;
        
        public User(String Supplier_ID, String Supplier_Name, String Email){
            this.Supplier_ID=Supplier_ID;
            this.Supplier_Name=Supplier_Name;
            this.Email=Email;
        }
        public String SID(){
            return Supplier_ID;
        }
        public String SName(){
            return Supplier_Name;
        }
        public String SEmail(){
            return Email;
        }
        
}
