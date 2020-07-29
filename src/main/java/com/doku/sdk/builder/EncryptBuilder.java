//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.doku.sdk.builder;

import com.google.common.hash.Hashing;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class EncryptBuilder {
    private String sha256;

    EncryptBuilder(final String sha256) {
        this.sha256 = Hashing.sha256()
                .hashString(sha256, StandardCharsets.UTF_8)
                .toString();
    }

    public static EncryptionBuilder builder() {
        return new EncryptionBuilder();
    }

    public static class EncryptionBuilder {
        private String sha256;

        EncryptionBuilder() {
        }

        public EncryptionBuilder sha256(final String sha256) {
            this.sha256 = sha256;
            return this;
        }
        public EncryptBuilder build() {
            return new EncryptBuilder(this.sha256);
        }
    }
}
