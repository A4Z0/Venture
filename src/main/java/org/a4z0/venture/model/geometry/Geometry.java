package org.a4z0.venture.model.geometry;

import org.a4z0.venture.model.Model;
import org.a4z0.venture.ID;

import java.util.Dictionary;
import java.util.Hashtable;

/**
* ...
*/

public enum Geometry  {
    BLOCK("geometry_world_block");

    /**
    * Dictionary that will attach the geometric Objects represented as a {@link Model} to a unique {@link org.a4z0.venture.ID}.
    */

    private static Dictionary<ID, Model> GEOMETRY_DICTIONARY;

    private final ID ID;

    /**
    * Construct a {@link Geometry}.
    *
    * @param id {@link CharSequence Char Sequence} that is attached to a {@link Model}.
    */

    Geometry(CharSequence id) {
        this(new ID(id));
    }

    /**
    * Construct a {@link Geometry}.
    *
    * @param id {@link ID} that is attached to a {@link Model}.
    */

    Geometry(ID id) {
        this.ID = id;
    }

    /**
    * @return a {@link Model}.
    */

    public Model getModel() {
        return GEOMETRY_DICTIONARY.get(this.ID);
    }

    /**
    * Init the Geometry {@link Dictionary}.
    */

    public static void init() {
        if(GEOMETRY_DICTIONARY != null)
            throw new IllegalStateException("It isn't possible to init the Geometry dictionary as it is already started.");

        GEOMETRY_DICTIONARY = new Hashtable<>();
        GEOMETRY_DICTIONARY.put(BLOCK.ID, Model.getFrom("assets/geometry/block.obj"));
    }
}