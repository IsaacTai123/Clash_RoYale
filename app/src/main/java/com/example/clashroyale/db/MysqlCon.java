package com.example.clashroyale.db;

import android.util.Log;

import com.example.clashroyale.Enums;
import com.example.clashroyale.GlobalConfig;
import com.example.clashroyale.models.ICard;

import java.lang.reflect.WildcardType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCon implements IMysqlCon {
    String db_name = "clashRoyale";
    String mysql_ip = "150.117.238.70";
    String mysql_port = "3306";
    String username = "isaac";
    String password = "abc";
    String url = "jdbc:mysql://" + mysql_ip + ":" + mysql_port + "/" + db_name;
    Connection connection;

    public MysqlCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB", "LOAD SUCCESSFUL");
        } catch (ClassNotFoundException e) {
            Log.e("DB", "LOADED FAILED");
            return;
        }

        try {

            connection = DriverManager.getConnection(url, username, password);
            Log.v("DB", "Remote Connection SUCCESS");
        } catch (SQLException e) {
            Log.e("DB", "Remote Connection FAILED");
            Log.e("DB", e.toString());
        }
    }

    public String getCardTroopData(int playerId) {
        String data = "";
        String[] field = {"cardName", "HitPoints", "speed", "HitSpeed", "Targets", "Range", "Damage", "Range Damage", "Area Damage", "Projectile Range", "slowdown Duration", "Elixir", "Type"};
        String[] values = new String[field.length];
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM Card_Troop Where playerId = " + playerId + ";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                for (int i = 0; i < field.length; i++) {
                    values[i] = result.getString(field[i]);
                }
                data += GlobalConfig.stringToJsonFormat(result.getString(field[0]), field, values);
                if (result.next()) {
                    data = data + ",";
                }
            }
            data = data + "}";

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getSpecificCardData(ICard card, int playerId) {
        String data = "";
        String[] field = {"cardName", "HitPoints", "Speed", "HitSpeed", "Targets", "Range", "Damage", "Range Damage", "Area Damage", "Projectile Range", "slowdown Duration", "Elixir", "Type"};
        String[] values = new String[field.length];
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM Card_Troop Where playerId = "+ playerId + " and cardName='" + card.getCardName()+"';";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                for (int i = 0; i < field.length; i++) {
                    values[i] = result.getString(field[i]);
                }
                data += GlobalConfig.stringToJsonFormat(result.getString(field[0]), field, values);
                if (result.next()) {
                    data = data + ",";
                }
            }
            data = data + "}";

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getCardSpellData(int playerId) {
        String data = "";
        String[] field = {"CardName", "Radius", "Effective Duration", "Area Damage", "Crown Tower Damage", "Elixir", "Type"};
        String[] values = new String[field.length];
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM Card_Spell Where playerId = " + playerId + ";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                for (int i = 0; i < field.length; i++) {
                    values[i] = result.getString(field[i]);
                }
                data += GlobalConfig.stringToJsonFormat(result.getString(field[0]), field, values);
                if (result.next()) {
                    data = data + ",";
                }
            }
            data = data + "}";

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setCardDeck(int playerId, ICard[] cards) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = String.format("INSERT INTO `CardDeck` VALUES (null, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                   playerId, cards[0].getCardName(), cards[1].getCardName(), cards[2].getCardName(), cards[3].getCardName(),
                   cards[4].getCardName(), cards[5].getCardName(), cards[6].getCardName(), cards[7].getCardName());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getCardDeck(int playerId, int DeckId) {
        String[] cardDeck = {"card1", "card2", "card3", "card4", "card5", "card6", "card7", "card8"};
        String[] cards = new String[8];
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM `CardDeck` Where playerId = 1 and id=" + DeckId +";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                for (int i=0; i<cardDeck.length; i++) {
                    cards[i] = result.getString(cardDeck[i]);
                }
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }


}
