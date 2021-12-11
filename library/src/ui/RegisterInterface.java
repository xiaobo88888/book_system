package ui;

import component.BackGroundPanel;
import util.PathUtils;
import util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterInterface {
    JFrame jFrame = new JFrame("注册界面");

    final int WIDTH = 500;
    final int HEIGHT = 400;

    //组装视图
    public void init() throws IOException {
        //设置窗口相关属性
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);

        jFrame.setIconImage(ImageIO.read(new File(PathUtils.getPath("三角形.png"))));

        jFrame.setResizable(false);

        //设置窗口内容
        BackGroundPanel zcPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getPath("注册.png"))));

        //组装注册相关元素
        Box box = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();

        JLabel uLabel = new JLabel("用户名:");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box mBox = Box.createHorizontalBox();

        JLabel mLabel = new JLabel("密    码:");
        JTextField mField = new JTextField(15);

        mBox.add(mLabel);
        mBox.add(Box.createHorizontalStrut(20));
        mBox.add(mField);

        //组装手机号
        Box pBox = Box.createHorizontalBox();

        JLabel pLabel = new JLabel("手机号:");
        JTextField pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装性别
        Box gBox = Box.createHorizontalBox();

        JLabel gLabel = new JLabel("性    别:");
        JRadioButton maleBtn = new JRadioButton("男", true);//默认被选中
        JRadioButton femaleBtn = new JRadioButton("女", false);

        //实现单选效果
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(femaleBtn);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(100));

        //组装验证码
        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("验证码:");
        JTextField cField = new JTextField(4);
        JLabel cImg = new JLabel(new ImageIcon(PathUtils.getPath("验证码.png")));//这里的验证码就用一张图片代替了
        //给某个组件设置提示信息
        cImg.setToolTipText("点击也刷新不了");
        //给验证码组件设置点击事件
        cImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cImg.setIcon(new ImageIcon(PathUtils.getPath("验证码2.png")));//这里是另一张验证码图片
                cImg.updateUI();
            }
        });

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);
        cBox.add(cImg);

        //组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton registerBtn = new JButton("注册");
        JButton backBtn = new JButton("返回登录页面");

        bBox.add(registerBtn);
        bBox.add(Box.createHorizontalStrut(20));
        bBox.add(backBtn);

        //为按钮添加监听
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取注册信息
                String username = uField.getText().trim();
                String password = mField.getText().trim();
                String phone = pField.getText().trim();
                String gender = bg.isSelected(maleBtn.getModel()) ? maleBtn.getText() : femaleBtn.getText();
                String checkCode = cField.getText().trim();

                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("phone", phone);
                params.put("gender", gender);
                params.put("checkCode", checkCode);

                //访问后台接口
                // TODO 这个目前不会

                //这里假设已经注册成功了
                JOptionPane.showMessageDialog(jFrame, "注册成功，即将返回登录页面");
                try {
                    new MainInterface().init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                jFrame.dispose();

            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //返回到登录页面即可
                try {
                    new MainInterface().init();

                    jFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        //组装全部组件
        box.add(Box.createVerticalStrut(50));
        box.add(uBox);
        box.add(Box.createVerticalStrut(20));
        box.add(mBox);
        box.add(Box.createVerticalStrut(20));
        box.add(pBox);
        box.add(Box.createVerticalStrut(20));
        box.add(gBox);
        box.add(Box.createVerticalStrut(20));
        box.add(cBox);
        box.add(Box.createVerticalStrut(20));
        box.add(bBox);


        zcPanel.add(box);

        jFrame.add(zcPanel);

        jFrame.setVisible(true);


    }
    /*public static void main(String[] args) {
        try {
            new RegisterInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
