package org.a4z0.venture;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
* ...
*/

public class Array3d<T> implements Iterable<T> {

    public final T[] ARRAY;

    protected final int MAX_ARRAY_X, MIN_ARRAY_X;
    protected final int MAX_OFFSET_X, MIN_OFFSET_X;

    protected final int MAX_ARRAY_Y, MIN_ARRAY_Y;
    protected final int MAX_OFFSET_Y, MIN_OFFSET_Y;

    protected final int MAX_ARRAY_Z, MIN_ARRAY_Z;
    protected final int MAX_OFFSET_Z, MIN_OFFSET_Z;

    protected final int ARRAY_X_LENGTH;
    protected final int ARRAY_Y_LENGTH;
    protected final int ARRAY_Z_LENGTH;

    protected final int ARRAY_LENGTH;

    /**
    * Construct a {@link Array3d}.
    *
    * @param ARRAY_TYPE ...
    * @param MAX_ARRAY_X ...
    * @param MAX_ARRAY_Y ...
    * @param MAX_ARRAY_Z ...
    */

    public Array3d(
        Class<?> ARRAY_TYPE,
        int MAX_ARRAY_X,
        int MAX_ARRAY_Y,
        int MAX_ARRAY_Z
    ) {
        this(ARRAY_TYPE, MAX_ARRAY_X, -MAX_ARRAY_X, MAX_ARRAY_Y, -MAX_ARRAY_Y, MAX_ARRAY_Z, -MAX_ARRAY_Z);
    }

    /**
    * Construct a {@link Array3d}.
    *
    * @param ARRAY_TYPE ...
    * @param MAX_ARRAY_X ...
    * @param MIN_ARRAY_X ...
    * @param MAX_ARRAY_Y ...
    * @param MIN_ARRAY_Y ...
    * @param MAX_ARRAY_Z ...
    * @param MIN_ARRAY_Z ...
    */

    public Array3d(
        Class<?> ARRAY_TYPE,
        int MAX_ARRAY_X,
        int MIN_ARRAY_X,
        int MAX_ARRAY_Y,
        int MIN_ARRAY_Y,
        int MAX_ARRAY_Z,
        int MIN_ARRAY_Z
    ) {
        this(ARRAY_TYPE, MAX_ARRAY_X, 0, MIN_ARRAY_X, 0, MAX_ARRAY_Y, 0, MIN_ARRAY_Y, 0, MAX_ARRAY_Z, 0, MIN_ARRAY_Z, 0);
    }

    /**
    * ...
    *
    * @param ARRAY_TYPE ...
    * @param MAX_ARRAY_X ...
    * @param MAX_OFFSET_X ...
    * @param MIN_ARRAY_X ...
    * @param MIN_OFFSET_X ...
    * @param MAX_ARRAY_Y ...
    * @param MAX_OFFSET_Y ...
    * @param MIN_ARRAY_Y ...
    * @param MIN_OFFSET_Y ...
    * @param MAX_ARRAY_Z ...
    * @param MAX_OFFSET_Z ...
    * @param MIN_ARRAY_Z ...
    * @param MIN_OFFSET_Z ...
    */

    @SuppressWarnings("unchecked")
    public Array3d(
        Class<?> ARRAY_TYPE,
        int MAX_ARRAY_X,
        int MAX_OFFSET_X,
        int MIN_ARRAY_X,
        int MIN_OFFSET_X,
        int MAX_ARRAY_Y,
        int MAX_OFFSET_Y,
        int MIN_ARRAY_Y,
        int MIN_OFFSET_Y,
        int MAX_ARRAY_Z,
        int MAX_OFFSET_Z,
        int MIN_ARRAY_Z,
        int MIN_OFFSET_Z
    ) {
        this.MAX_ARRAY_X = Math.max(0, MAX_ARRAY_X);
        this.MAX_OFFSET_X = Math.max(0, MAX_OFFSET_X);
        this.MIN_ARRAY_X = Math.min(-0, MIN_ARRAY_X);
        this.MIN_OFFSET_X = Math.min(0, MIN_OFFSET_X);

        this.MAX_ARRAY_Y = Math.max(0, MAX_ARRAY_Y);
        this.MAX_OFFSET_Y = Math.max(0, MAX_OFFSET_Y);
        this.MIN_ARRAY_Y = Math.min(-0, MIN_ARRAY_Y);
        this.MIN_OFFSET_Y = Math.min(0, MIN_OFFSET_Y);

        this.MAX_ARRAY_Z = Math.max(0, MAX_ARRAY_Z);
        this.MAX_OFFSET_Z = Math.max(0, MAX_OFFSET_Z);
        this.MIN_ARRAY_Z = Math.min(-0, MIN_ARRAY_Z);
        this.MIN_OFFSET_Z = Math.min(0, MIN_OFFSET_Z);

        this.ARRAY_X_LENGTH = (this.MAX_ARRAY_X + Math.abs(this.MIN_ARRAY_X));
        this.ARRAY_Y_LENGTH = (this.MAX_ARRAY_Y + Math.abs(this.MIN_ARRAY_Y));
        this.ARRAY_Z_LENGTH = (this.MAX_ARRAY_Z + Math.abs(this.MIN_ARRAY_Z));

        this.ARRAY_LENGTH = this.ARRAY_X_LENGTH * this.ARRAY_Y_LENGTH * this.ARRAY_X_LENGTH;

        this.ARRAY = (T[]) Array.newInstance(ARRAY_TYPE, this.ARRAY_LENGTH);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param ARRAY_VALUE ...
    */

    public int set(int X, int Y, int Z, T ARRAY_VALUE) {
        int INDEX = translate(X, Y, Z, this);

        this.ARRAY[INDEX] = ARRAY_VALUE;

        return INDEX;
    }

    /**
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    public T get(int X, int Y, int Z) {
        return this.get(translate(X, Y, Z, this));
    }

    /**
    * @param INDEX ...
    *
    * @return ...
    */

    public T get(int INDEX) {
        return this.ARRAY[INDEX];
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    */

    public void remove(int X, int Y, int Z) {
        this.set(X, Y, Z, null);
    }

    /**
    * @return ...
    */

    public int getLength() {
        return this.ARRAY_LENGTH;
    }

    /**
    * @param INDEX ...
    * @param MAX_LENGTH ...
    * @param MIN_LENGTH ...
    *
    * @return ...
    */

    protected static int translate(
        int INDEX,
        int MAX_LENGTH,
        int MAX_OFFSET,
        int MIN_LENGTH,
        int MIN_OFFSET
    ) {
        if((INDEX + MAX_OFFSET - MIN_OFFSET) > MAX_LENGTH)
            throw new ArrayIndexOutOfBoundsException("Index " + INDEX + " is beyond maximum limit " + MAX_LENGTH + ".");

        if((INDEX + MAX_OFFSET - MIN_OFFSET) < MIN_LENGTH)
            throw new ArrayIndexOutOfBoundsException("Index " + INDEX + " is below minimum limit " + MIN_LENGTH + ".");

        return (INDEX + MAX_OFFSET - MIN_OFFSET) - MIN_LENGTH;
    }

    /**
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param ARRAY ...
    *
    * @return ...
    */

    public static int translate(
        int X,
        int Y,
        int Z,
        Array3d<?> ARRAY
    ) {
        return translate(X, Y, Z, ARRAY.MAX_ARRAY_X, ARRAY.MAX_OFFSET_X, ARRAY.MIN_ARRAY_X, ARRAY.MIN_OFFSET_X, ARRAY.MAX_ARRAY_Y, ARRAY.MAX_OFFSET_Y, ARRAY.MIN_ARRAY_Y, ARRAY.MIN_OFFSET_Y, ARRAY.MAX_ARRAY_Z, ARRAY.MAX_OFFSET_Z, ARRAY.MIN_ARRAY_Z, ARRAY.MIN_OFFSET_Z);
    }

    /**
    * ...
    *
    * @param X ...
    * @param Y ...
    * @param Z ...
    * @param MAX_X ...
    * @param MIN_X ...
    * @param MAX_Y ...
    * @param MIN_Y ...
    * @param MAX_Z ...
    * @param MIN_Z ...
    *
    * @return ...
    */

    protected static int translate(
        int X,
        int Y,
        int Z,
        int MAX_X,
        int MAX_OFFSET_X,
        int MIN_X,
        int MIN_OFFSET_X,
        int MAX_Y,
        int MAX_OFFSET_Y,
        int MIN_Y,
        int MIN_OFFSET_Y,
        int MAX_Z,
        int MAX_OFFSET_Z,
        int MIN_Z,
        int MIN_OFFSET_Z
    ) {
        return
            translate(X, MAX_X, MAX_OFFSET_X, MIN_X, MIN_OFFSET_X)
            + (translate(Y, MAX_Y, MAX_OFFSET_Y, MIN_Y, MIN_OFFSET_Y) * (MAX_X + (Math.abs(MIN_X))))
            + (translate(Z, MAX_Z, MAX_OFFSET_Z, MIN_Z, MIN_OFFSET_Z) * (MAX_X + (Math.abs(MIN_X))) * (MAX_Y + (Math.abs(MIN_Y))));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int CURRENT_INDEX = 0;

            @Override
            public boolean hasNext() {
                return CURRENT_INDEX < ARRAY_LENGTH;
            }

            @Override
            public T next() {
                if(!hasNext())
                    throw new ArrayIndexOutOfBoundsException("No more elements in the array.");

                return ARRAY[CURRENT_INDEX++];
            }
        };
    }
}