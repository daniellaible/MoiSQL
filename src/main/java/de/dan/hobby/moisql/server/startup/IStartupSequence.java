package de.dan.hobby.moisql.server.startup;

/**
 * @author Daniel Laible
 * @since 0.1.6
 *
 * The strategy interface for the startup routine
 */
public interface IStartupSequence {

  void commence(StartupContext context);

}
