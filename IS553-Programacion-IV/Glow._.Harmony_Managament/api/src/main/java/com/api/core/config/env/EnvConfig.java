package com.api.core.config.env;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.cdimascio.dotenv.Dotenv;
import com.api.core.config.env.enums.EnvKeys;
import com.api.core.config.env.interfaces.IEnvConfig;

/**
 * The EnvConfig class is responsible for loading environment variables from a .env file
 * and providing access to these variables through the get method.
 **/
public class EnvConfig implements IEnvConfig{
  private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);
  private final Dotenv dotenv;

  public EnvConfig() {
    File envFile = new File(".env");

    if (envFile.exists() == false) {
      logger.error(".env file not found");
      throw new IllegalStateException(".env file not found");
    }

    logger.info(".env file found");
    dotenv = Dotenv.load();
  }

  /**
   * Retrieves the value associated with the specified environment key.
   *
   * @param key the environment key whose value is to be retrieved
   * @return the value associated with the specified environment key
   * @throws IllegalStateException if the .env file is not found or could not be
   *                               loaded
   */
  public String get(EnvKeys key) {
    if (dotenv == null) {
      throw new IllegalStateException(".env file not found or could not be loaded");
    }
    return dotenv.get(key.name());
  }
}
