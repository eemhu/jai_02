package com.teragrep.jai_02.keystore;/*
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Provides a factory, which builds KeyStores from the
 * defined path and format.
 */
public class KeyStoreFactory {

    private final KeyStoreFormat keyStoreFormat;
    private final String path;
    private final char[] pw;

    public KeyStoreFactory(String path, char[] pw) {
        this(new KeyStoreFormat(), path, pw);
    }

    public KeyStoreFactory(KeyStoreFormat ka, String path, char[] pw) {
        this.keyStoreFormat = ka;
        this.path = path;
        this.pw = pw;
    }

    public KeyStore build() {
        final KeyStore ks;

        if (path == null || path.isEmpty()) {
            throw new IllegalStateException("KeyStorePath is required, cannot be null or empty.");
        }

        try {
            ks = KeyStore.getInstance(keyStoreFormat.toString());
            // If the path points to a file that doesn't exist, initialize an empty keyStore
            final Path pathToKeyStore = Paths.get(path);
            if (Files.notExists(pathToKeyStore, LinkOption.NOFOLLOW_LINKS)) {
                ks.load(null, null);
            } else {
                InputStream inputStream = Files.newInputStream(pathToKeyStore);
                ks.load(inputStream, pw);
                inputStream.close();
            }

        } catch (KeyStoreException | NoSuchAlgorithmException e) {
            // This should not happen as the getInstance() method is only invocated with known good
            // algorithm strings
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize InputStream to KeyStore path <[" + path + "]>\n" + e);
        }

        return ks;
    }
}
