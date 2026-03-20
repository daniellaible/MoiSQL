package de.dan.hobby.moisql.datatype;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is the generic interface of all different kinds of datatypes that are used
 * in MoiSql
 */
public interface IDataType<E> {

  <E> E getValue();

  String getDataType();

  byte[] toByteArray();

  <E> E fromByteArray(byte[] bytes);
}
