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
package com.teragrep.jai_02.keystore;

import com.teragrep.jai_02.entry.EntryAlias;
import com.teragrep.jai_02.entry.EntryAliasString;
import com.teragrep.jai_02.entry.Split;
import com.teragrep.jai_02.password.Salt;
import com.teragrep.jai_02.password.SaltFactory;
import com.teragrep.jai_02.user.UserNameImpl;
import com.teragrep.jai_02.user.UserNameValid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntryAliasTest {

    @Test
    public void testEntryAliasAndEntryAliasStringEquals() {
        String name = "userish";

        Split split = new Split(':');

        UserNameImpl username = new UserNameImpl(name, split);
        UserNameValid userNameValid = username.asValid();

        SaltFactory saltFactory = new SaltFactory();
        Salt salt = saltFactory.createSalt();

        int iterationCount = 100_000;

        EntryAlias entryAlias = new EntryAlias(userNameValid, salt, iterationCount, split);

        EntryAliasString entryAliasString = new EntryAliasString(entryAlias.toString(), split);
        EntryAlias other = entryAliasString.toEntryAlias();

        Assertions.assertEquals(entryAlias, other);
    }

    @Test
    public void testEntryAliasToString() {
        String name = "userish";

        Split split = new Split(':');
        UserNameImpl username = new UserNameImpl(name, split);
        UserNameValid userNameValid = username.asValid();
        SaltFactory saltFactory = new SaltFactory();
        Salt salt = saltFactory.createSalt();
        int iterationCount = 100_000;

        EntryAlias entryAlias = new EntryAlias(userNameValid, salt, iterationCount, split);
        Assertions.assertEquals(name + split + salt + split + iterationCount, entryAlias.toString());
    }

    @Test
    public void testEntryAliasStringToEntryAlias() {
        String name = "userish";

        Split split = new Split(':');
        SaltFactory saltFactory = new SaltFactory();
        Salt salt = saltFactory.createSalt();

        int iterationCount = 100_000;

        String str = name + split + salt + split + iterationCount;
        EntryAliasString entryAliasString = new EntryAliasString(str, split);
        EntryAlias ea = entryAliasString.toEntryAlias();

        Assertions.assertEquals(name, ea.userName().toString());
        Assertions.assertEquals(salt, ea.salt());
        Assertions.assertEquals(iterationCount, ea.iterationCount());
        Assertions.assertEquals(str, ea.toString());
    }
}
