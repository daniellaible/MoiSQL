package de.dan.hobby.moisql.server.startup;

import de.dan.hobby.moisql.server.OSType;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.1.6
 * <p>
 * Part of the StartupSequence Strategy
 * Determines the operating system the server is working with
 */
public class OSDetector implements IStartupSequence {

  private static final Logger logger = LoggerFactory.getLogger(OSDetector.class);

  private static final String OS_NAME = "os.name";
  private static final String GENERIC = "generic";
  private static final String WIN = "win";
  private static final String NIX = "nix";
  private static final String NUX = "nux";
  private static final String AIX = "aix";
  private static final String MAC = "mac";
  private static final String SUNOS = "sunos";
  private static final String LOGGER_DETECTED_OS_IS = "Detected OS is: {}";

  public void commence(StartupContext context) {
    String os = System.getProperty(OS_NAME, GENERIC).toLowerCase(Locale.ENGLISH);
    if (os.contains(WIN)) {
      context.osType = OSType.WINDOWS;
    } else if (os.contains(NIX) || os.contains(NUX) || os.contains(AIX)) {
      context.osType = OSType.LINUX;
    } else if (os.contains(MAC)) {
      context.osType = OSType.MAC;
    } else if (os.contains(SUNOS)) {
      context.osType = OSType.SOLARIS;
    } else {
      context.osType = OSType.OTHER;
    }
    logger.info(LOGGER_DETECTED_OS_IS, context.osType);
  }
}
