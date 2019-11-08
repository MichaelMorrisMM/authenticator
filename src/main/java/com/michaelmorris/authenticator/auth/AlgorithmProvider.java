package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.algorithms.Algorithm;

public interface AlgorithmProvider {

    Algorithm getAlgorithm();

}
