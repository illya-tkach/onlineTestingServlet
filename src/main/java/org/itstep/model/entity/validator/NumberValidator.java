package org.itstep.model.entity.validator;


public class NumberValidator {

    /**
     * Check whether form parameter is a valid Integer
     *
     * @param numberParam form parameter
     * @return true if user input is valid Integer, false otherwise
     */
    public boolean isValidInteger(String numberParam) {
        try {
            Integer.parseInt(numberParam);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
