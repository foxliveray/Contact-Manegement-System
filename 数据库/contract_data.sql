
use contractdb;
-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Cus20131211182300001', 'HB company', 'hubei wuhan', '11111111111', '12121212', '430000', 'Bank Of China', '621661***', 0);
INSERT INTO `customer` VALUES (2, 'Cus20131211182300002', 'BJ company', 'beijing', '22222222', '34213467', '100000', 'Agricultural Bank of China', '622848***', 0);
INSERT INTO `customer` VALUES (3, 'Cus20131211182300003', 'Jack Wang', 'Shanghai', '14231116', '45678234', '200000', 'Industrial and Commercial Bank of China Limited', '530990***', 0);

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `function` VALUES (1, '001', 'QCHT', null, null, 0);
INSERT INTO `function` VALUES (2, '002', 'DGHT', null, null, 0);
INSERT INTO `function` VALUES (3, '003', 'CXHT', null, null, 0);
INSERT INTO `function` VALUES (4, '004', 'SCHT', null, null, 0);
INSERT INTO `function` VALUES (5, '005', 'HQHT', null, null, 0);
INSERT INTO `function` VALUES (6, '006', 'SPHT', null, null, 0);
INSERT INTO `function` VALUES (7, '007', 'QDHT', null, null, 0);
INSERT INTO `function` VALUES (8, '008', 'FPHQ', null, null, 0);
INSERT INTO `function` VALUES (9, '009', 'FPSP', null, null, 0);
INSERT INTO `function` VALUES (10, '010', 'FPQD', null, null, 0);
INSERT INTO `function` VALUES (11, '011', 'LCCX', null, null, 0);
INSERT INTO `function` VALUES (12, '012', 'XZYH', null, null, 0);
INSERT INTO `function` VALUES (13, '013', 'BJYH', null, null, 0);
INSERT INTO `function` VALUES (14, '014', 'CXYH', null, null, 0);
INSERT INTO `function` VALUES (15, '015', 'SCYH', null, null, 0);
INSERT INTO `function` VALUES (16, '016', 'XZJS', null, null, 0);
INSERT INTO `function` VALUES (17, '017', 'BJJS', null, null, 0);
INSERT INTO `function` VALUES (18, '018', 'CXJS', null, null, 0);
INSERT INTO `function` VALUES (19, '019', 'SCJS', null, null, 0);
INSERT INTO `function` VALUES (20, '020', 'XZGN', null, null, 0);
INSERT INTO `function` VALUES (21, '021', 'BJGN', null, null, 0);
INSERT INTO `function` VALUES (22, '022', 'CXGN', null, null, 0);
INSERT INTO `function` VALUES (23, '023', 'SCGN', null, null, 0);
INSERT INTO `function` VALUES (24, '024', 'PZQX', null, null, 0);
INSERT INTO `function` VALUES (25, '025', 'XZKH', null, null, 0);
INSERT INTO `function` VALUES (26, '026', 'BJKH', null, null, 0);
INSERT INTO `function` VALUES (27, '027', 'CXKH', null, null, 0);
INSERT INTO `function` VALUES (28, '028', 'SCKH', null, null, 0);
INSERT INTO `function` VALUES (29, '029', 'CXRZ', null, null, 0);
INSERT INTO `function` VALUES (30, '030', 'SCRZ', null, null, 0);


-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', 'To implement the system management and contract management', '003,004,008,009,010,011,012,013,014,015,016,017,018,019,020,021,022,023,024,025,026,027,028,029,030', 0);
INSERT INTO `role` VALUES (2, 'operator', 'operate contract', '001,002,003,005,006,007,011,027', 0);

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 0);
INSERT INTO `user` VALUES (2, 'thomas', '123456', 0);
INSERT INTO `user` VALUES (3, 'lucy', '123456', 0);
INSERT INTO `user` VALUES (4, 'lily', '123456', 0);
INSERT INTO `user` VALUES (5, 'jack', '123456', 0);
INSERT INTO `user` VALUES (6, 'sanri', '123456', 0);

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES (1, 1, 1, 'admin', 0);
INSERT INTO `t_right` VALUES (2, 2, 2, 'operator', 0);
INSERT INTO `t_right` VALUES (3, 3, 2, 'operator', 0);
INSERT INTO `t_right` VALUES (4, 4, 2, 'operator', 0);
INSERT INTO `t_right` VALUES (5, 5, 2, 'operator', 0);
