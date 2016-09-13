package br.ifpb.pod.utils;

import br.ifpb.pod.entity.*;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natarajan
 */
public class DocumentUtils {

    public static List<Pessoa>  getPessoasFromJson(String json) {

        List<Pessoa> result = new ArrayList<>();

        JSONObject obj = new JSONObject(json);

        JSONArray pessoas = obj.getJSONArray("pessoas");

        for(int i = 0 ; i < pessoas.length() ; i++){


            Pessoa p = new Pessoa();
            p.fromJSONObject(pessoas.getJSONObject(i));

            result.add(p);
        }

        return result;

    }

}
