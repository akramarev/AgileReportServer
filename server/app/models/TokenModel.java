package models;

import assets.MorphiaObject;
import assets.ObjectIdSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;

import java.util.Date;

@Entity("tokens")
public class TokenModel {
    @Id
    @JsonIgnore
    public ObjectId id;

    public UserModel user;

    @Indexed
    public String token;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    private TokenModel()
    { }

    public static TokenModel Get(String token)
    {
        return MorphiaObject.datastore.find(TokenModel.class).filter("token", token).get();
    }

    public static TokenModel Generate(UserModel user)
    {
        TokenModel m = new TokenModel();
        m.user = user;
        m.dateCreatedUtc = new Date();
        m.token = RandomStringUtils.randomAlphanumeric(256);

        return m;
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }
}
