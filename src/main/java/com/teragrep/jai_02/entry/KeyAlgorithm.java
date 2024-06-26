package com.teragrep.jai_02.entry;/*
 * Java Authentication Info jai_02
 * Copyright (C) 2021  Suomen Kanuuna Oy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://github.com/teragrep/teragrep/blob/main/LICENSE>.
 *
 *
 * Additional permission under GNU Affero General Public License version 3
 * section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with other code, such other code is not for that reason alone subject to any
 * of the requirements of the GNU Affero GPL version 3 as long as this Program
 * is the same Program as licensed from Suomen Kanuuna Oy without any additional
 * modifications.
 *
 * Supplemented terms under GNU Affero General Public License version 3
 * section 7
 *
 * Origin of the software must be attributed to Suomen Kanuuna Oy. Any modified
 * versions must be marked as "Modified version of" The Program.
 *
 * Names of the licensors and authors may not be used for publicity purposes.
 *
 * No rights are granted for use of trade names, trademarks, or service marks
 * which are in The Program if any.
 *
 * Licensee must indemnify licensors and authors for any liability that these
 * contractual assumptions impose on licensors and authors.
 *
 * To the extent this program is licensed as part of the Commercial versions of
 * Teragrep, the applicable Commercial License may apply to this file if you as
 * a licensee so wish it.
 */

/**
 * An object containing the algorithm used for SecretKeys.
 */
public class KeyAlgorithm {
    private final KeySecretAlgorithm keySecretAlgorithm;

    public enum KeySecretAlgorithm {
        // Default algorithm for SecretKey
        // Another options can be added in the future
        PBKDF2WithHmacSHA1("PBKDF2WithHmacSHA1");

        private final String algo;

        KeySecretAlgorithm(String algo) {
            this.algo = algo;
        }

        @Override
        public String toString() {
            return this.algo;
        }
    }

    /**
     * Defaults algorithms to:
     * <ul>
     * <li>For SecretKeys, PBKDF2WithHmacSHA1.</li>
     * </ul>
     */
    public KeyAlgorithm() {
        this(KeySecretAlgorithm.PBKDF2WithHmacSHA1);
    }

    /**
     * Customize the used algorithms for individual SecretKeys.
     * @param keySecretAlgorithm SecretKey algorithm
     */
    public KeyAlgorithm(KeySecretAlgorithm keySecretAlgorithm) {
        this.keySecretAlgorithm = keySecretAlgorithm;
    }


    public KeySecretAlgorithm get() {
        return this.keySecretAlgorithm;
    }
}
