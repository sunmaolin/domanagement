Ext.define('Index.Main',{
    extend:'Ext.panel.Panel',
    xtype:'Main',
    id:'Main',
    layout:'border',
    initComponent:function () {
        var Main_me=this;
        Main_me.callParent();
        Main_me.loadData();
    },
    loadData:function () {

    },
    items: [{
        title:'宿舍管理',
        region:'north',
        height: 100,
        bodyStyle: 'background-image: url(/images/th.jpg)',
        html: '<div style="font-size: 25px;text-align: center;margin-top: 5px"><b>齐鲁工业大学宿舍管理系统</b><br>' +
            '<span style="font-size: 15px">联系电话：010101</span></div>'
    },{
        title: '宿舍浏览',
        region: 'west',
        width: 180,
        split: true,
        collapsible: true,
        items:{
            html:'你好'
        }
    }, {
        title:'功能菜单',
        region: 'east',
        width: 180,
        collapsible: true,
        collapsed:true,
        split:true,
        items:{
            html:'你好'
        }
    },{
        region: 'center',
        xtype:'tabpanel',
        layout: 'fit'
    }]
});