package bot.news;

import bot.BaseBotDB;

import java.sql.PreparedStatement;
import java.util.List;

public class NewBotDB extends BaseBotDB {
    public NewBotDB() {
    }

    void inserts(List<NewDetail> data) {
        appSql.connect(connection -> {
            try {
                connection.setAutoCommit(false);
                String[] columns = new String[]{
                        "id", "type", "title", "description", "commentsCount", "views", "hasVideos",
                        "isFeatured", "slug", "thumbnail", "publishedAt", "content", "genre", "thumbnailSmall"
                };
                StringBuilder columnName = new StringBuilder();
                StringBuilder markValue = new StringBuilder();
                StringBuilder update = new StringBuilder();
                for (int i = 0; i < columns.length; i++) {
                    String column = columns[i];
                    columnName.append(String.format("%s, ", column));
                    markValue.append("?, ");
                    if (i != 0 && i != 1) {
                        update.append(String.format("%s = VALUES(%s), ", column, column));
                    }
                }
                String format = "INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s";
                String sql = String.format(
                        format,
                        appStorage.config.database.table.news,
                        columnName.substring(0, columnName.length() - 2),
                        markValue.substring(0, markValue.length() - 2),
                        update.substring(0, update.length() - 2)
                );
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int index = 0; index < data.size(); index++) {
                    NewDetail item = data.get(index);
                    statement.setInt(1, item.id);
                    statement.setInt(2, item.type);
                    statement.setString(3, item.title);
                    statement.setString(4, item.description);
                    statement.setInt(5, item.commentsCount);
                    statement.setInt(6, item.views);
                    statement.setBoolean(7, item.hasVideos);
                    statement.setBoolean(8, item.isFeatured);
                    statement.setString(9, item.slug);
                    statement.setString(10, item.thumbnail);
                    statement.setString(11, item.publishedAt);
                    statement.setString(12, item.content);
                    statement.setString(13, gson.toJson(item.genre));
                    statement.setString(14, item.thumbnailSmall);
                    statement.addBatch();
                }
                int[] updateCounts = statement.executeBatch();
                System.out.println(gson.toJson(updateCounts));
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}