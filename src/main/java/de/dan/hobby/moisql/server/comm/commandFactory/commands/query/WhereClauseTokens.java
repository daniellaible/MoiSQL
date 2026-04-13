package de.dan.hobby.moisql.server.comm.commandFactory.commands.query;

public record WhereClauseTokens(String columnName, Operator operator, String value) {

}
