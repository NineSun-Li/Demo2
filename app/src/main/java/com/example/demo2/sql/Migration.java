package com.example.demo2.sql;

import java.nio.file.attribute.FileAttribute;
import java.time.Period;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
/**
 * 升级数据库
 * */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion!=newVersion){
            RealmObjectSchema favorites = schema.get("favorites");
            favorites.addField("favoritesid",String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("favoritesid","1");
                        }
                    }).removeField("name");
            oldVersion++;
        }
    }
}
