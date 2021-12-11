package component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BookManageComponent extends Box {
    final int WIDTH = 850;
    final int HEIGHT = 600;

    private JTable jTable;//表格
    private Vector<String> titles;//表格标题
    private Vector<Vector> data;//表格信息
    private TableModel tableModel;//表格模型

    JFrame jFrame = null;

    public BookManageComponent(JFrame jFrame) {//接受了一个JFrame
        super(BoxLayout.Y_AXIS);//指定垂直布局

        this.jFrame = jFrame;

        //组装视图

        //创建顶部组件
        JPanel jPanel = new JPanel();

        Color color = new Color(203, 220, 217);
        jPanel.setBackground(color);//设置背景色

        jPanel.setMaximumSize(new Dimension(WIDTH, 40));//设置宽高
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));//布局管理变为流水式，并且从右开始

        //创建按钮
        JButton addBtn = new JButton("添加");
        JButton updateBtn = new JButton("修改");
        JButton deleteBtn = new JButton("删除");

        //为按钮添加监听

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出一个对话框
                new AddBookDialog(jFrame, "添加图书", true).setVisible(true);//由于该组件没有JFrame，所以需要传递一个JFrame过来

                //刷新数据
                //TODO 这个也暂时不会
            }
        });

        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //获取当前表格选中的行
                int selectedRow = jTable.getSelectedRow();//如果有选中的条目，则返回id，如果没有，则弹出窗口

                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jFrame, "请选择要删除的行");
                    return;
                }

                //获取当前表格选中的条目
                String id = tableModel.getValueAt(selectedRow, 0).toString();


                //弹出一个对话框
                new UpdateBookDialog(jFrame, "修改图书", true, id).setVisible(true);

                //重新请求数据
                //TODO 这个也暂时不会


            }
        });

        deleteBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前表格选中的行
                int selectedRow = jTable.getSelectedRow();//如果有选中的条目，则返回id，如果没有，则弹出窗口

                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jFrame, "请选择要删除的行");
                    return;
                }


                //防止误操作
                int result = JOptionPane.showConfirmDialog(jFrame, "确认要删除选中的条目吗？", "删除信息", JOptionPane.YES_NO_OPTION);
                if(result != JOptionPane.YES_OPTION){
                    return;
                }

                //获取当前表格选中的条目
                String id = tableModel.getValueAt(selectedRow, 0).toString();


                //访问后台，删除数据，再刷新数据
                //TODO 这个也不会

                JOptionPane.showMessageDialog(jFrame, "删除成功");



            }
        });
        jPanel.add(addBtn);
        jPanel.add(updateBtn);
        jPanel.add(deleteBtn);

        this.add(jPanel);//将JPanel放入Box中


        //组装表格
        String[] ts = {"编号", "书名", "简介", "作者", "价格", "库存"};

        titles = new Vector<>();

        for (int i = 0; i < ts.length; i++) {
            titles.add(ts[i]);
        }

        data = new Vector<>();

        tableModel = new DefaultTableModel(data, titles);

        jTable = new JTable(tableModel){
            //表格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //设置只能单选
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        this.add(new JScrollPane(jTable));//将JTable放入Box中

    }


}
