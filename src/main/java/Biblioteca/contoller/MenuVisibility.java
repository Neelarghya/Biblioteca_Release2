package Biblioteca.contoller;

import Biblioteca.model.Customer;

public enum MenuVisibility {
    ALWAYS {
        @Override
        boolean isDisplayable(Customer customer) {
            return true;
        }
    },
    LOGGED_IN {
        @Override
        boolean isDisplayable(Customer customer) {
            return customer != null;
        }
    },
    LOGGED_OUT {
        @Override
        boolean isDisplayable(Customer customer) {
            return customer == null;
        }
    },
    NEVER {
        @Override
        boolean isDisplayable(Customer customer) {
            return false;
        }
    };

    abstract boolean isDisplayable(Customer customer);
}
