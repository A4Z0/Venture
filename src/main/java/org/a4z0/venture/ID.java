package org.a4z0.venture;

import java.util.Arrays;

/**
* A {@link ID} represented by a character sequence.
*/

@Deprecated
public class ID {

    protected final char[] ID_CHARS;

    /**
    * Construct a {@link ID}.
    *
    * @param ID_CHAR_SEQUENCE {@link CharSequence Char Sequence} of this {@link ID}.
    */

    public ID(CharSequence ID_CHAR_SEQUENCE) {
        this(ID_CHAR_SEQUENCE.toString().toCharArray());
    }

    /**
    * Construct a {@link ID}.
    *
    * @param ID_CHARS {@link CharSequence Chars} of this {@link ID}.
    */

    public ID(char ... ID_CHARS) {
        for(char ID_CHAR : ID_CHARS)
            if(ID_CHAR != '_' && !Character.isLowerCase(ID_CHAR))
                throw new IllegalArgumentException("Char isn't a letter or an underscore.");

        this.ID_CHARS = ID_CHARS;
    }

    /**
    * ...
    *
    * @param charSequence {@link CharSequence Char Sequence} to match.
    *
    * @return true if matches, false otherwise.
    */

    public boolean equals(CharSequence charSequence) {
        return this.equals(charSequence.toString().toCharArray());
    }

    /**
    * ...
    *
    * @param ID_CHARS {@link CharSequence Chars} to match.
    *
    * @return true if matches, false otherwise.
    */

    public boolean equals(char[] ID_CHARS) {
        return this.equals(new ID(ID_CHARS));
    }

    @Override
    public int hashCode() {
        return (this.getClass().getName().hashCode() * Arrays.hashCode(ID_CHARS));
    }
}