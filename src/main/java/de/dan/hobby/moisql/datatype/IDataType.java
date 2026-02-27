package de.dan.hobby.moisql.datatype;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is just a marker interface, all datatypes will inherit it.
 * This way a db row is just an array of IDataType
 */
public interface IDataType {

  String getName();
}
