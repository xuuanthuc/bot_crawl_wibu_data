package bot.wallpaper;

import bot.BaseBotDB;
import java.sql.PreparedStatement;
import java.util.List;

public class WallpaperBotDB extends BaseBotDB {
    public WallpaperBotDB() {
    }

    void inserts(List<WallpaperItems> data) {
        appSql.connect(connection -> {
            try {
                connection.setAutoCommit(false);
                String[] columns = new String[]{
                        "id", "type", "contentType", "downloadCount", "title", "tags", "authorId",
                        "authorName", "authorAvatarUrl", "shareUrl", "thumbUrl", "downloadReference", "size", "licenseType","previewUrl", "wallpaperSize"
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
                        appStorage.config.database.table.wallpaper,
                        columnName.substring(0, columnName.length() - 2),
                        markValue.substring(0, markValue.length() - 2),
                        update.substring(0, update.length() - 2)
                );
                System.out.println(sql);
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int index = 0; index < data.size(); index++) {
                    WallpaperItems item = data.get(index);
                    statement.setString(1, item.id);
                    statement.setInt(2, item.type);
                    statement.setString(3, item.contentType);
                    statement.setLong(4, item.downloadCount);
                    statement.setString(5, item.title);
                    statement.setString(6, String.valueOf(item.tags));
                    statement.setString(7, item.authorId);
                    statement.setString(8, item.authorName);
                    statement.setString(9, item.authorAvatarUrl);
                    statement.setString(10, item.shareUrl);
                    statement.setString(11, item.thumbUrl);
                    statement.setString(12, item.downloadReference);
                    statement.setLong(13, item.size);
                    statement.setLong(14, item.licenseType);
                    statement.setString(15, item.previewUrl);
                    statement.setString(16, gson.toJson(item.wallpaperSize));
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
