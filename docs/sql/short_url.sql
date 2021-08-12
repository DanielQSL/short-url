-- ----------------------------
-- Table structure for short_url_map
-- ----------------------------
DROP TABLE IF EXISTS `short_url_map`;
CREATE TABLE `short_url_map`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `short_url`   bigint(20) unsigned DEFAULT NULL COMMENT '短链',
    `long_url`    varchar(10)       DEFAULT NULL COMMENT '长链',
    `md5`         char(32)          DEFAULT NULL COMMENT '长链md5',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='短链与长连映射表';