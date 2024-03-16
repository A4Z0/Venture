package org.a4z0.venture.model;

/**
* A {@link Token} represented by a character sequence.
*/

public class Token {

    protected final char[] chars;

    /**
    * Construct a {@link Token}.
    *
    * @param charSequence {@link CharSequence Char Sequence} of this {@link Token}.
    */

    public Token(CharSequence charSequence) {
        this(charSequence.toString().toCharArray());
    }

    /**
    * Construct a {@link Token}.
    *
    * @param chars {@link CharSequence Chars} of this {@link Token}.
    */

    public Token(char ...chars) {
        this.chars = chars;
    }

    /**
    * @param charSequence {@link CharSequence Char Sequence} to match.
    *
    * @return true if matches, false otherwise.
    */

    public boolean matches(CharSequence charSequence) {
        return this.matches(charSequence.toString().toCharArray());
    }

    /**
    * @param chars {@link CharSequence Chars} to match.
    *
    * @return true if matches, false otherwise.
    */

    public boolean matches(char[] chars) {
        if(chars.length != this.chars.length)
            return false;

        for(int i = 0; i < this.chars.length; i++)
            if(this.chars[i] != chars[i])
                return false;

        return true;
    }
}