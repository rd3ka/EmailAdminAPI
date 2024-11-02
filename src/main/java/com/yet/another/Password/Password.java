    package com.yet.another.Password;

    public class Password {

        private String password;
        public Password() {}
        public Password(int length) {
            setPassword(length);       
        }

        public int getLength() {
            return this.password.length();
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private void setPassword(int length) {
            this.password =  PasswordUtils
                            .generatePassword(length);
        }

        public final String getPassword() {
            return this.password;
        }
    }
