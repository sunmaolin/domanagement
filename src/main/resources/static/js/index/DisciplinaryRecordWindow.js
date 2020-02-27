Ext.define('Index.DisciplinaryRecordWindow', {
    extend: 'Ext.window.Window',
    xtype: 'disciplinaryRecordWindow',
    layout: 'vbox',
    flag:null,
    url:null,
    width:610,
    height:600,
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData:function(){
        var me = this;
        //加载宿舍楼store
        me.floorStore=new Ext.create('Ext.data.Store',{
           fields:['fid','fname'],
           proxy:{
               type: 'ajax',
               url: '/dormitory/findAllFloor',
               method:'GET',
               reader: {
                   type: 'json',
                   root: 'data'
               }
           }
        });

        me.dormitoryStore=new Ext.create('Ext.data.Store',{
            fields:['did','fid','dname'],
            proxy:{
                type: 'ajax',
                url: '/dormitory/findDormitoryByFid',
                method:'POST',
                extraParams:{
                  fid:null
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        //违纪记录store
        me.disciplinaryStore=Ext.create('Ext.data.Store',{
            fields:['pid','fname','dname','sid','content','createUser','createTime','image'],
            proxy: {
                type: 'ajax',
                url:me.url,
                method:'GET',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad: true
        });
    },
    loadComponent: function () {
        var me=this;
        //宿舍楼下拉框
        var floorComb=Ext.create('Ext.form.ComboBox',{
           fieldLabel:'宿舍楼',
           labelWidth:50,
           width:160,
           editable:false,
           store:me.floorStore,
           displayField:'fname',
           valueField:'fid',
           emptyText:'请选择宿舍楼',
           name:'fid',
           listeners:{
               change:function (field,newValue) {
                   //实现二级联动
                   me.dormitoryStore.getProxy().extraParams={fid:newValue};
                   me.dormitoryStore.load();
               }
           }
        });
        //宿舍下拉框
        var dormitoryComb=Ext.create('Ext.form.ComboBox',{
            fieldLabel:'宿舍',
            labelWidth:40,
            width:150,
            margin:'0 0 0 20',
            editable:false,
            store:me.dormitoryStore,
            displayField:'dname',
            valueField:'did',
            emptyText:'请选择宿舍',
            name:'did'
        });
        //违纪内容
        var disciplinaryContent=Ext.widget('textareafield',{
           fieldLabel:'违纪内容',
           labelWidth:60,
           width:310,
           height:100,
           margin:'10 0 0 0',
           name:'content'
        });
        //文件上传
        var fileUpLoad=Ext.widget('filefield',{
            name:'photo',
            fieldLabel:'违纪照片',
            labelWidth:60,
            width:250,
            margin:'10 0 0 0',
            buttonText:'选择图片'
        });
        //确定按钮
        var ensureButton=Ext.create('Ext.button.Button',{
            text:'确定',
            margin:'0 30 0 100',
            handler:function () {

            }
        });
        //删除按钮
        var deleteButton=Ext.create('Ext.button.Button',{
            text:'删除',
            handler:function () {

            }
        });
        //表单
        var formPanel=Ext.create('Ext.form.Panel',{
            layout:'vbox',
            bodyPadding:'10 0 0 120',
            width:605,
            height:250,
            items:[{
                border:0,
                layout:'hbox',
                items:[floorComb,dormitoryComb]
            },disciplinaryContent,fileUpLoad,{
                border:0,
                layout:'hbox',
                margin:'30 0 0 0',
                defaults:{
                    width:50,
                    height:30
                },
                items:[ensureButton,deleteButton]
            }]
        });

        me.add([formPanel]);

        me.add(Ext.create('Ext.grid.Panel',{
            scroll:true,
            height:318,
            width:600,
            columns:[
                {header:'宿舍楼',dataIndex:'fname',width:80},
                {header:'宿舍',dataIndex:'dname',width:80},
                {header:'违纪内容',dataIndex:'content',width:120},
                {header:'违纪时间',dataIndex:'createTime'},
                {header:'记录人',dataIndex:'createUser'},
                {header:'图片',renderer:function (value,record,item) {
                        if (item.data.image){
                            return '<a href="http://localhost:8080/images/'+item.data.image+'" target="_blank">查看违纪图片</a>';
                        } else {
                            return '<a>无违纪图片</a>';
                        }
                    }
                }
            ],
            store:me.disciplinaryStore
        }));
    }
});