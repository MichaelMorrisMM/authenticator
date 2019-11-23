package com.michaelmorris.authenticator.util;

import com.auth0.jwt.algorithms.Algorithm;

public interface AlgorithmProvider {

    Algorithm getAlgorithm();

}
