package com.api.core.config.env.interfaces;

import com.api.core.config.env.enums.EnvKeys;

/**
 * Interface for environment configuration.
 * Provides a method to retrieve configuration values based on specified keys.
 */
public interface IEnvConfig {
  String get(EnvKeys key);
}