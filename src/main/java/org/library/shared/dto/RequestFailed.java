package org.library.shared.dto;

import java.io.Serializable;
import java.util.List;

public record RequestFailed(
    List<String> errors
) implements Serializable { }
