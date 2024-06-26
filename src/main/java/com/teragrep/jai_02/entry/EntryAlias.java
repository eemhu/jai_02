/*
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
package com.teragrep.jai_02.entry;

import com.teragrep.jai_02.password.Salt;
import com.teragrep.jai_02.user.UserName;
import com.teragrep.jai_02.user.UserNameValid;

import java.util.Objects;

/**
 * Provides an alias for a KeyStore Entry.
 * Contains the username, salt, iteration count and split character.
 */
public class EntryAlias {

    private final UserNameValid userNameValid;
    private final Salt salt;
    private final int iterationCount;
    private final Split split;

    public EntryAlias(UserNameValid userNameValid, Salt salt, int iterationCount, Split split) {
        this.userNameValid = userNameValid;
        this.salt = salt;
        this.iterationCount = iterationCount;
        this.split = split;
    }

    @Override
    public String toString() {
        return userNameValid.toString() + split + salt + split + iterationCount;
    }

    public UserName userName() {
        return userNameValid;
    }

    public Salt salt() {
        return salt;
    }

    public int iterationCount() {
        return iterationCount;
    }

    public Split split() {
        return split;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EntryAlias entryAlias = (EntryAlias) o;
        return iterationCount == entryAlias.iterationCount() && Objects.equals(
                userNameValid, entryAlias.userName()) && Objects.equals(salt, entryAlias.salt()) && Objects.equals(split, entryAlias.split());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNameValid, salt, iterationCount, split);
    }

}
