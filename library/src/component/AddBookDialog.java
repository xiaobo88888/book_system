package component;

import util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog {

    final int WIDTH = 400;
    final int HEIGHT = 300;

    public AddBookDialog(JFrame jFrame, String title, boolean isModel){
        super(jFrame, title, isModel);

        //设置窗口大小和位置
        this.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);


        Box box = Box.createVerticalBox();


        //组装图书名称
        Box nBox = Box.createHorizontalBox();

        JLabel nLabel = new JLabel("图书名称:");
        JTextField nField = new JTextField(15);

        nBox.add(nLabel);
        nBox.add(Box.createHorizontalStrut(20));
        nBox.add(nField);

        //组装图书库存
        Box cBox = Box.createHorizontalBox();

        JLabel cLabel = new JLabel("图书库存:");
        JTextField cField = new JTextField(15);

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);

        //组装图书作者
        Box aBox = Box.createHorizontalBox();

        JLabel aLabel = new JLabel("图书作者:");
        JTextField aField = new JTextField(15);

        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(20));
        aBox.add(aField);

        //组装图书价格
        Box pBox = Box.createHorizontalBox();

        JLabel pLabel = new JLabel("图书价格:");
        JTextField pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装图书简介
        Box jBox = Box.createHorizontalBox();

        JLabel jLabel = new JLabel("图书简介:");
        JTextArea jArea = new JTextArea(3, 15);

        jBox.add(jLabel);
        jBox.add(Box.createHorizontalStrut(20));
        jBox.add(new JScrollPane(jArea));

        //组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户数据
                String name = nField.getText().trim();
                String stock = cField.getText().trim();
                String author = aField.getText().trim();
                String price = pField.getText().trim();
                String desc = jArea.getText().trim();

                //访问后台接口
                // TODO 这个暂时不会

                JOptionPane.showMessageDialog(jFrame,"添加成功！！！");
                dispose();//当前窗口隐藏
            }
        });

        bBox.add(addBtn);


        //组装全部
        box.add(Box.createVerticalStrut(20));
        box.add(nBox);
        box.add(Box.createVerticalStrut(15));
        box.add(cBox);
        box.add(Box.createVerticalStrut(15));
        box.add(aBox);
        box.add(Box.createVerticalStrut(15));
        box.add(pBox);
        box.add(Box.createVerticalStrut(15));
        box.add(jBox);
        box.add(Box.createVerticalStrut(15));
        box.add(bBox);


        //为了左右有间隔，再定义一个水平的Box
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(box);
        hBox.add(Box.createHorizontalStrut(20));


        this.add(hBox);

    }


}
