CREATE TABLE
    act_de_model
    (
        id VARCHAR(255) NOT NULL,
        name VARCHAR(400) NOT NULL,
        model_key VARCHAR(400) NOT NULL,
        description VARCHAR(4000),
        model_comment VARCHAR(4000),
        created DATETIME(6),
        created_by VARCHAR(255),
        last_updated DATETIME(6),
        last_updated_by VARCHAR(255),
        version INT,
        model_editor_json longtext,
        thumbnail longblob,
        model_type INT,
        PRIMARY KEY (id),
        INDEX idx_proc_mod_key (model_key)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_dmn_databasechangelog
    (
        ID VARCHAR(255) NOT NULL,
        AUTHOR VARCHAR(255) NOT NULL,
        FILENAME VARCHAR(255) NOT NULL,
        DATEEXECUTED DATETIME NOT NULL,
        ORDEREXECUTED INT NOT NULL,
        EXECTYPE VARCHAR(10) NOT NULL,
        MD5SUM VARCHAR(35),
        DESCRIPTION VARCHAR(255),
        COMMENTS VARCHAR(255),
        TAG VARCHAR(255),
        LIQUIBASE VARCHAR(20),
        CONTEXTS VARCHAR(255),
        LABELS VARCHAR(255)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_dmn_databasechangeloglock
    (
        ID INT NOT NULL,
        LOCKED bit NOT NULL,
        LOCKGRANTED DATETIME,
        LOCKEDBY VARCHAR(255),
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_dmn_decision_table
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        VERSION_ INT,
        KEY_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        DEPLOYMENT_ID_ VARCHAR(255),
        PARENT_DEPLOYMENT_ID_ VARCHAR(255),
        TENANT_ID_ VARCHAR(255),
        RESOURCE_NAME_ VARCHAR(255),
        DESCRIPTION_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_dmn_deployment
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        DEPLOY_TIME_ DATETIME,
        TENANT_ID_ VARCHAR(255),
        PARENT_DEPLOYMENT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_dmn_deployment_resource
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        DEPLOYMENT_ID_ VARCHAR(255),
        RESOURCE_BYTES_ longblob,
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_evt_log
    (
        LOG_NR_ bigint NOT NULL AUTO_INCREMENT,
        TYPE_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        EXECUTION_ID_ VARCHAR(64),
        TASK_ID_ VARCHAR(64),
        TIME_STAMP_ TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL,
        USER_ID_ VARCHAR(255),
        DATA_ longblob,
        LOCK_OWNER_ VARCHAR(255),
        LOCK_TIME_ TIMESTAMP(3),
        IS_PROCESSED_ TINYINT DEFAULT 0,
        PRIMARY KEY (LOG_NR_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_fo_databasechangelog
    (
        ID VARCHAR(255) NOT NULL,
        AUTHOR VARCHAR(255) NOT NULL,
        FILENAME VARCHAR(255) NOT NULL,
        DATEEXECUTED DATETIME NOT NULL,
        ORDEREXECUTED INT NOT NULL,
        EXECTYPE VARCHAR(10) NOT NULL,
        MD5SUM VARCHAR(35),
        DESCRIPTION VARCHAR(255),
        COMMENTS VARCHAR(255),
        TAG VARCHAR(255),
        LIQUIBASE VARCHAR(20),
        CONTEXTS VARCHAR(255),
        LABELS VARCHAR(255)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_fo_databasechangeloglock
    (
        ID INT NOT NULL,
        LOCKED bit NOT NULL,
        LOCKGRANTED DATETIME,
        LOCKEDBY VARCHAR(255),
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_fo_form_definition
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        VERSION_ INT,
        KEY_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        DEPLOYMENT_ID_ VARCHAR(255),
        PARENT_DEPLOYMENT_ID_ VARCHAR(255),
        TENANT_ID_ VARCHAR(255),
        RESOURCE_NAME_ VARCHAR(255),
        DESCRIPTION_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_fo_form_deployment
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        DEPLOY_TIME_ DATETIME,
        TENANT_ID_ VARCHAR(255),
        PARENT_DEPLOYMENT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_fo_form_resource
    (
        ID_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255),
        DEPLOYMENT_ID_ VARCHAR(255),
        RESOURCE_BYTES_ longblob,
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_fo_submitted_form
    (
        ID_ VARCHAR(255) NOT NULL,
        FORM_ID_ VARCHAR(255) NOT NULL,
        TASK_ID_ VARCHAR(255),
        PROC_INST_ID_ VARCHAR(255),
        PROC_DEF_ID_ VARCHAR(255),
        SUBMITTED_DATE_ DATETIME,
        SUBMITTED_BY_ VARCHAR(255),
        FORM_VALUES_ID_ VARCHAR(255),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    act_ge_bytearray
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        NAME_ VARCHAR(255),
        DEPLOYMENT_ID_ VARCHAR(64),
        BYTES_ longblob,
        GENERATED_ TINYINT,
        PRIMARY KEY (ID_),
        INDEX ACT_FK_BYTEARR_DEPL (DEPLOYMENT_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ge_property
    (
        NAME_ VARCHAR(64) NOT NULL,
        VALUE_ VARCHAR(300),
        REV_ INT,
        PRIMARY KEY (NAME_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_actinst
    (
        ID_ VARCHAR(64) NOT NULL,
        PROC_DEF_ID_ VARCHAR(64) NOT NULL,
        PROC_INST_ID_ VARCHAR(64) NOT NULL,
        EXECUTION_ID_ VARCHAR(64) NOT NULL,
        ACT_ID_ VARCHAR(255) NOT NULL,
        TASK_ID_ VARCHAR(64),
        CALL_PROC_INST_ID_ VARCHAR(64),
        ACT_NAME_ VARCHAR(255),
        ACT_TYPE_ VARCHAR(255) NOT NULL,
        ASSIGNEE_ VARCHAR(255),
        START_TIME_ DATETIME(3) NOT NULL,
        END_TIME_ DATETIME(3),
        DURATION_ bigint,
        DELETE_REASON_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_HI_ACT_INST_START (START_TIME_),
        INDEX ACT_IDX_HI_ACT_INST_END (END_TIME_),
        INDEX ACT_IDX_HI_ACT_INST_PROCINST (PROC_INST_ID_, ACT_ID_),
        INDEX ACT_IDX_HI_ACT_INST_EXEC (EXECUTION_ID_, ACT_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_attachment
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        USER_ID_ VARCHAR(255),
        NAME_ VARCHAR(255),
        DESCRIPTION_ VARCHAR(4000),
        TYPE_ VARCHAR(255),
        TASK_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        URL_ VARCHAR(4000),
        CONTENT_ID_ VARCHAR(64),
        TIME_ DATETIME(3),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_comment
    (
        ID_ VARCHAR(64) NOT NULL,
        TYPE_ VARCHAR(255),
        TIME_ DATETIME(3) NOT NULL,
        USER_ID_ VARCHAR(255),
        TASK_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        ACTION_ VARCHAR(255),
        MESSAGE_ VARCHAR(4000),
        FULL_MSG_ longblob,
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_detail
    (
        ID_ VARCHAR(64) NOT NULL,
        TYPE_ VARCHAR(255) NOT NULL,
        PROC_INST_ID_ VARCHAR(64),
        EXECUTION_ID_ VARCHAR(64),
        TASK_ID_ VARCHAR(64),
        ACT_INST_ID_ VARCHAR(64),
        NAME_ VARCHAR(255) NOT NULL,
        VAR_TYPE_ VARCHAR(255),
        REV_ INT,
        TIME_ DATETIME(3) NOT NULL,
        BYTEARRAY_ID_ VARCHAR(64),
        DOUBLE_ DOUBLE,
        LONG_ bigint,
        TEXT_ VARCHAR(4000),
        TEXT2_ VARCHAR(4000),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_HI_DETAIL_PROC_INST (PROC_INST_ID_),
        INDEX ACT_IDX_HI_DETAIL_ACT_INST (ACT_INST_ID_),
        INDEX ACT_IDX_HI_DETAIL_TIME (TIME_),
        INDEX ACT_IDX_HI_DETAIL_NAME (NAME_),
        INDEX ACT_IDX_HI_DETAIL_TASK_ID (TASK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_identitylink
    (
        ID_ VARCHAR(64) NOT NULL,
        GROUP_ID_ VARCHAR(255),
        TYPE_ VARCHAR(255),
        USER_ID_ VARCHAR(255),
        TASK_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_HI_IDENT_LNK_USER (USER_ID_),
        INDEX ACT_IDX_HI_IDENT_LNK_TASK (TASK_ID_),
        INDEX ACT_IDX_HI_IDENT_LNK_PROCINST (PROC_INST_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_procinst
    (
        ID_ VARCHAR(64) NOT NULL,
        PROC_INST_ID_ VARCHAR(64) NOT NULL,
        BUSINESS_KEY_ VARCHAR(255),
        PROC_DEF_ID_ VARCHAR(64) NOT NULL,
        START_TIME_ DATETIME(3) NOT NULL,
        END_TIME_ DATETIME(3),
        DURATION_ bigint,
        START_USER_ID_ VARCHAR(255),
        START_ACT_ID_ VARCHAR(255),
        END_ACT_ID_ VARCHAR(255),
        SUPER_PROCESS_INSTANCE_ID_ VARCHAR(64),
        DELETE_REASON_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        NAME_ VARCHAR(255),
        PRIMARY KEY (ID_),
        CONSTRAINT PROC_INST_ID_ UNIQUE (PROC_INST_ID_),
        INDEX ACT_IDX_HI_PRO_INST_END (END_TIME_),
        INDEX ACT_IDX_HI_PRO_I_BUSKEY (BUSINESS_KEY_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_taskinst
    (
        ID_ VARCHAR(64) NOT NULL,
        PROC_DEF_ID_ VARCHAR(64),
        TASK_DEF_KEY_ VARCHAR(255),
        PROC_INST_ID_ VARCHAR(64),
        EXECUTION_ID_ VARCHAR(64),
        NAME_ VARCHAR(255),
        PARENT_TASK_ID_ VARCHAR(64),
        DESCRIPTION_ VARCHAR(4000),
        OWNER_ VARCHAR(255),
        ASSIGNEE_ VARCHAR(255),
        START_TIME_ DATETIME(3) NOT NULL,
        CLAIM_TIME_ DATETIME(3),
        END_TIME_ DATETIME(3),
        DURATION_ bigint,
        DELETE_REASON_ VARCHAR(4000),
        PRIORITY_ INT,
        DUE_DATE_ DATETIME(3),
        FORM_KEY_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_HI_TASK_INST_PROCINST (PROC_INST_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_hi_varinst
    (
        ID_ VARCHAR(64) NOT NULL,
        PROC_INST_ID_ VARCHAR(64),
        EXECUTION_ID_ VARCHAR(64),
        TASK_ID_ VARCHAR(64),
        NAME_ VARCHAR(255) NOT NULL,
        VAR_TYPE_ VARCHAR(100),
        REV_ INT,
        BYTEARRAY_ID_ VARCHAR(64),
        DOUBLE_ DOUBLE,
        LONG_ bigint,
        TEXT_ VARCHAR(4000),
        TEXT2_ VARCHAR(4000),
        CREATE_TIME_ DATETIME(3),
        LAST_UPDATED_TIME_ DATETIME(3),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_HI_PROCVAR_PROC_INST (PROC_INST_ID_),
        INDEX ACT_IDX_HI_PROCVAR_NAME_TYPE (NAME_, VAR_TYPE_),
        INDEX ACT_IDX_HI_PROCVAR_TASK_ID (TASK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_id_group
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        NAME_ VARCHAR(255),
        TYPE_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_id_info
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        USER_ID_ VARCHAR(64),
        TYPE_ VARCHAR(64),
        KEY_ VARCHAR(255),
        VALUE_ VARCHAR(255),
        PASSWORD_ longblob,
        PARENT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_id_membership
    (
        USER_ID_ VARCHAR(64) NOT NULL,
        GROUP_ID_ VARCHAR(64) NOT NULL,
        PRIMARY KEY (USER_ID_, GROUP_ID_),
        INDEX ACT_FK_MEMB_GROUP (GROUP_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_id_user
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        FIRST_ VARCHAR(255),
        LAST_ VARCHAR(255),
        EMAIL_ VARCHAR(255),
        PWD_ VARCHAR(255),
        PICTURE_ID_ VARCHAR(64),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_procdef_info
    (
        ID_ VARCHAR(64) NOT NULL,
        PROC_DEF_ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        INFO_JSON_ID_ VARCHAR(64),
        PRIMARY KEY (ID_),
        CONSTRAINT ACT_UNIQ_INFO_PROCDEF UNIQUE (PROC_DEF_ID_),
        INDEX ACT_IDX_INFO_PROCDEF (PROC_DEF_ID_),
        INDEX ACT_FK_INFO_JSON_BA (INFO_JSON_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_re_deployment
    (
        ID_ VARCHAR(64) NOT NULL,
        NAME_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        KEY_ VARCHAR(255),
        TENANT_ID_ VARCHAR(255),
        DEPLOY_TIME_ TIMESTAMP(3),
        ENGINE_VERSION_ VARCHAR(255),
        PRIMARY KEY (ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_re_model
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        NAME_ VARCHAR(255),
        KEY_ VARCHAR(255),
        CATEGORY_ VARCHAR(255),
        CREATE_TIME_ TIMESTAMP(3),
        LAST_UPDATE_TIME_ TIMESTAMP(3),
        VERSION_ INT,
        META_INFO_ VARCHAR(4000),
        DEPLOYMENT_ID_ VARCHAR(64),
        EDITOR_SOURCE_VALUE_ID_ VARCHAR(64),
        EDITOR_SOURCE_EXTRA_VALUE_ID_ VARCHAR(64),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_FK_MODEL_SOURCE (EDITOR_SOURCE_VALUE_ID_),
        INDEX ACT_FK_MODEL_SOURCE_EXTRA (EDITOR_SOURCE_EXTRA_VALUE_ID_),
        INDEX ACT_FK_MODEL_DEPLOYMENT (DEPLOYMENT_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_re_procdef
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        CATEGORY_ VARCHAR(255),
        NAME_ VARCHAR(255),
        KEY_ VARCHAR(255) NOT NULL,
        VERSION_ INT NOT NULL,
        DEPLOYMENT_ID_ VARCHAR(64),
        RESOURCE_NAME_ VARCHAR(4000),
        DGRM_RESOURCE_NAME_ VARCHAR(4000),
        DESCRIPTION_ VARCHAR(4000),
        HAS_START_FORM_KEY_ TINYINT,
        HAS_GRAPHICAL_NOTATION_ TINYINT,
        SUSPENSION_STATE_ INT,
        TENANT_ID_ VARCHAR(255),
        ENGINE_VERSION_ VARCHAR(255),
        PRIMARY KEY (ID_),
        CONSTRAINT ACT_UNIQ_PROCDEF UNIQUE (KEY_, VERSION_, TENANT_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_deadletter_job
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        TYPE_ VARCHAR(255) NOT NULL,
        EXCLUSIVE_ TINYINT(1),
        EXECUTION_ID_ VARCHAR(64),
        PROCESS_INSTANCE_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        EXCEPTION_STACK_ID_ VARCHAR(64),
        EXCEPTION_MSG_ VARCHAR(4000),
        DUEDATE_ TIMESTAMP(3),
        REPEAT_ VARCHAR(255),
        HANDLER_TYPE_ VARCHAR(255),
        HANDLER_CFG_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_FK_DEADLETTER_JOB_EXECUTION (EXECUTION_ID_),
        INDEX ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE (PROCESS_INSTANCE_ID_),
        INDEX ACT_FK_DEADLETTER_JOB_PROC_DEF (PROC_DEF_ID_),
        INDEX ACT_FK_DEADLETTER_JOB_EXCEPTION (EXCEPTION_STACK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_event_subscr
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        EVENT_TYPE_ VARCHAR(255) NOT NULL,
        EVENT_NAME_ VARCHAR(255),
        EXECUTION_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        ACTIVITY_ID_ VARCHAR(64),
        CONFIGURATION_ VARCHAR(255),
        CREATED_ TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3) NOT NULL,
        PROC_DEF_ID_ VARCHAR(64),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_EVENT_SUBSCR_CONFIG_ (CONFIGURATION_),
        INDEX ACT_FK_EVENT_EXEC (EXECUTION_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_execution
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        PROC_INST_ID_ VARCHAR(64),
        BUSINESS_KEY_ VARCHAR(255),
        PARENT_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        SUPER_EXEC_ VARCHAR(64),
        ROOT_PROC_INST_ID_ VARCHAR(64),
        ACT_ID_ VARCHAR(255),
        IS_ACTIVE_ TINYINT,
        IS_CONCURRENT_ TINYINT,
        IS_SCOPE_ TINYINT,
        IS_EVENT_SCOPE_ TINYINT,
        IS_MI_ROOT_ TINYINT,
        SUSPENSION_STATE_ INT,
        CACHED_ENT_STATE_ INT,
        TENANT_ID_ VARCHAR(255),
        NAME_ VARCHAR(255),
        START_TIME_ DATETIME(3),
        START_USER_ID_ VARCHAR(255),
        LOCK_TIME_ TIMESTAMP(3),
        IS_COUNT_ENABLED_ TINYINT,
        EVT_SUBSCR_COUNT_ INT,
        TASK_COUNT_ INT,
        JOB_COUNT_ INT,
        TIMER_JOB_COUNT_ INT,
        SUSP_JOB_COUNT_ INT,
        DEADLETTER_JOB_COUNT_ INT,
        VAR_COUNT_ INT,
        ID_LINK_COUNT_ INT,
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_EXEC_BUSKEY (BUSINESS_KEY_),
        INDEX ACT_IDC_EXEC_ROOT (ROOT_PROC_INST_ID_),
        INDEX ACT_FK_EXE_PROCINST (PROC_INST_ID_),
        INDEX ACT_FK_EXE_PARENT (PARENT_ID_),
        INDEX ACT_FK_EXE_SUPER (SUPER_EXEC_),
        INDEX ACT_FK_EXE_PROCDEF (PROC_DEF_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_identitylink
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        GROUP_ID_ VARCHAR(255),
        TYPE_ VARCHAR(255),
        USER_ID_ VARCHAR(255),
        TASK_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_IDENT_LNK_USER (USER_ID_),
        INDEX ACT_IDX_IDENT_LNK_GROUP (GROUP_ID_),
        INDEX ACT_IDX_ATHRZ_PROCEDEF (PROC_DEF_ID_),
        INDEX ACT_FK_TSKASS_TASK (TASK_ID_),
        INDEX ACT_FK_IDL_PROCINST (PROC_INST_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_job
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        TYPE_ VARCHAR(255) NOT NULL,
        LOCK_EXP_TIME_ TIMESTAMP(3),
        LOCK_OWNER_ VARCHAR(255),
        EXCLUSIVE_ TINYINT(1),
        EXECUTION_ID_ VARCHAR(64),
        PROCESS_INSTANCE_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        RETRIES_ INT,
        EXCEPTION_STACK_ID_ VARCHAR(64),
        EXCEPTION_MSG_ VARCHAR(4000),
        DUEDATE_ TIMESTAMP(3),
        REPEAT_ VARCHAR(255),
        HANDLER_TYPE_ VARCHAR(255),
        HANDLER_CFG_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_FK_JOB_EXECUTION (EXECUTION_ID_),
        INDEX ACT_FK_JOB_PROCESS_INSTANCE (PROCESS_INSTANCE_ID_),
        INDEX ACT_FK_JOB_PROC_DEF (PROC_DEF_ID_),
        INDEX ACT_FK_JOB_EXCEPTION (EXCEPTION_STACK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_suspended_job
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        TYPE_ VARCHAR(255) NOT NULL,
        EXCLUSIVE_ TINYINT(1),
        EXECUTION_ID_ VARCHAR(64),
        PROCESS_INSTANCE_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        RETRIES_ INT,
        EXCEPTION_STACK_ID_ VARCHAR(64),
        EXCEPTION_MSG_ VARCHAR(4000),
        DUEDATE_ TIMESTAMP(3),
        REPEAT_ VARCHAR(255),
        HANDLER_TYPE_ VARCHAR(255),
        HANDLER_CFG_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_FK_SUSPENDED_JOB_EXECUTION (EXECUTION_ID_),
        INDEX ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE (PROCESS_INSTANCE_ID_),
        INDEX ACT_FK_SUSPENDED_JOB_PROC_DEF (PROC_DEF_ID_),
        INDEX ACT_FK_SUSPENDED_JOB_EXCEPTION (EXCEPTION_STACK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_task
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        EXECUTION_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        NAME_ VARCHAR(255),
        PARENT_TASK_ID_ VARCHAR(64),
        DESCRIPTION_ VARCHAR(4000),
        TASK_DEF_KEY_ VARCHAR(255),
        OWNER_ VARCHAR(255),
        ASSIGNEE_ VARCHAR(255),
        DELEGATION_ VARCHAR(64),
        PRIORITY_ INT,
        CREATE_TIME_ TIMESTAMP(3),
        DUE_DATE_ DATETIME(3),
        CATEGORY_ VARCHAR(255),
        SUSPENSION_STATE_ INT,
        TENANT_ID_ VARCHAR(255),
        FORM_KEY_ VARCHAR(255),
        CLAIM_TIME_ DATETIME(3),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_TASK_CREATE (CREATE_TIME_),
        INDEX ACT_FK_TASK_EXE (EXECUTION_ID_),
        INDEX ACT_FK_TASK_PROCINST (PROC_INST_ID_),
        INDEX ACT_FK_TASK_PROCDEF (PROC_DEF_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_timer_job
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        TYPE_ VARCHAR(255) NOT NULL,
        LOCK_EXP_TIME_ TIMESTAMP(3),
        LOCK_OWNER_ VARCHAR(255),
        EXCLUSIVE_ TINYINT(1),
        EXECUTION_ID_ VARCHAR(64),
        PROCESS_INSTANCE_ID_ VARCHAR(64),
        PROC_DEF_ID_ VARCHAR(64),
        RETRIES_ INT,
        EXCEPTION_STACK_ID_ VARCHAR(64),
        EXCEPTION_MSG_ VARCHAR(4000),
        DUEDATE_ TIMESTAMP(3),
        REPEAT_ VARCHAR(255),
        HANDLER_TYPE_ VARCHAR(255),
        HANDLER_CFG_ VARCHAR(4000),
        TENANT_ID_ VARCHAR(255),
        PRIMARY KEY (ID_),
        INDEX ACT_FK_TIMER_JOB_EXECUTION (EXECUTION_ID_),
        INDEX ACT_FK_TIMER_JOB_PROCESS_INSTANCE (PROCESS_INSTANCE_ID_),
        INDEX ACT_FK_TIMER_JOB_PROC_DEF (PROC_DEF_ID_),
        INDEX ACT_FK_TIMER_JOB_EXCEPTION (EXCEPTION_STACK_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    act_ru_variable
    (
        ID_ VARCHAR(64) NOT NULL,
        REV_ INT,
        TYPE_ VARCHAR(255) NOT NULL,
        NAME_ VARCHAR(255) NOT NULL,
        EXECUTION_ID_ VARCHAR(64),
        PROC_INST_ID_ VARCHAR(64),
        TASK_ID_ VARCHAR(64),
        BYTEARRAY_ID_ VARCHAR(64),
        DOUBLE_ DOUBLE,
        LONG_ bigint,
        TEXT_ VARCHAR(4000),
        TEXT2_ VARCHAR(4000),
        PRIMARY KEY (ID_),
        INDEX ACT_IDX_VARIABLE_TASK_ID (TASK_ID_),
        INDEX ACT_FK_VAR_EXE (EXECUTION_ID_),
        INDEX ACT_FK_VAR_PROCINST (PROC_INST_ID_),
        INDEX ACT_FK_VAR_BYTEARRAY (BYTEARRAY_ID_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_bin;
CREATE TABLE
    common_area
    (
        id CHAR(32) NOT NULL,
        parent_id CHAR(32),
        name VARCHAR(100),
        sort SMALLINT,
        type CHAR(5),
        remark VARCHAR(200),
        del_flg TINYINT(1),
        is_leaf TINYINT(1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_currency
    (
        id CHAR(3) NOT NULL,
        chinese_name VARCHAR(60),
        english_name VARCHAR(60),
        amount_dec_no TINYINT,
        exchange_rate_dec_no TINYINT,
        unit VARCHAR(30),
        status TINYINT,
        enabled TINYINT,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_department
    (
        id CHAR(32) NOT NULL,
        parent_id CHAR(32),
        name VARCHAR(60),
        area_code VARCHAR(52),
        type CHAR(5),
        code CHAR(6),
        grade CHAR(1),
        primary_person CHAR(32),
        addr VARCHAR(200),
        tel_no CHAR(20),
        fax_no CHAR(20),
        email VARCHAR(30),
        remark VARCHAR(200),
        del_flg TINYINT(1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_department_user_xref
    (
        department_id CHAR(32) NOT NULL,
        user_id CHAR(32) NOT NULL,
        create_by CHAR(32),
        create_time DATETIME,
        update_by CHAR(32),
        update_time DATETIME,
        PRIMARY KEY (department_id, user_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_dict
    (
        dict_type CHAR(3) NOT NULL,
        dict_value CHAR(32) NOT NULL,
        dict_label VARCHAR(80),
        remark VARCHAR(32),
        PRIMARY KEY (dict_type, dict_value)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_dict_type
    (
        id CHAR(3) NOT NULL,
        name VARCHAR(60),
        value_length INT,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_office
    (
        id CHAR(32) NOT NULL,
        parent_id CHAR(32),
        name VARCHAR(60),
        area_id CHAR(32),
        type CHAR(5),
        code CHAR(6),
        grade CHAR(1),
        primary_person CHAR(32),
        addr VARCHAR(200),
        tel_no CHAR(20),
        fax_no CHAR(20),
        email VARCHAR(30),
        remark VARCHAR(200),
        del_flg TINYINT(1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    common_unit
    (
        id CHAR(2) NOT NULL,
        name VARCHAR(20),
        SIGN VARCHAR(20),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    crm_customer
    (
        customer_no CHAR(12) NOT NULL,
        customer_type CHAR(1),
        customer_name VARCHAR(120),
        customer_group CHAR(32),
        area_code CHAR(6),
        remark VARCHAR(250),
        certificate_type CHAR(2),
        certificate_no CHAR(60),
        address VARCHAR(100),
        create_by CHAR(32),
        create_time DATETIME,
        modify_by CHAR(32),
        modify_time DATETIME,
        status TINYINT,
        PRIMARY KEY (customer_no)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    crm_customer_group
    (
        id CHAR(32) NOT NULL,
        parent_id CHAR(32),
        name VARCHAR(60),
        is_group TINYINT(1),
        create_time DATETIME,
        modify_time DATETIME,
        modify_by CHAR(32),
        status TINYINT,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    ledger_account_title
    (
        account_no CHAR(8) NOT NULL,
        name CHAR(60),
        short_name CHAR(60),
        account_character TINYINT,
        amount_direction TINYINT,
        balance_direction TINYINT,
        level TINYINT,
        table_flag TINYINT,
        parent CHAR(8),
        status TINYINT,
        PRIMARY KEY (account_no)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    ledger_tax_rate
    (
        id CHAR(12) NOT NULL,
        name VARCHAR(60),
        account_title CHAR(8),
        tax_rate DECIMAL(8,5),
        tax_method CHAR(1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci COMMENT='Ë°ÂÊ±í';
CREATE TABLE
    purchase_order
    (
        order_id CHAR(14) NOT NULL,
        create_time DATETIME,
        modify_time DATETIME,
        modify_by CHAR(32),
        status CHAR(1),
        supplier CHAR(12),
        total_taxes DECIMAL(15,2),
        total_amount DECIMAL(15,2),
        currency CHAR(3),
        entry CHAR(4),
        remark VARCHAR(120),
        PRIMARY KEY (order_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    purchase_order_detail
    (
        order_id CHAR(14) NOT NULL,
        item_id CHAR(10) NOT NULL,
        create_time DATETIME,
        modify_time DATETIME,
        modify_by CHAR(32),
        quantity DECIMAL(20,5),
        unit_price DECIMAL(15,2),
        tax_rate_id CHAR(12),
        amount DECIMAL(15,2),
        tax_amount DECIMAL(15,2),
        PRIMARY KEY (order_id, item_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_entry
    (
        warehouse_id CHAR(2) NOT NULL,
        id CHAR(2) NOT NULL,
        name VARCHAR(120),
        phone_no CHAR(20),
        mobile_no CHAR(14),
        address VARCHAR(140),
        account_title CHAR(8),
        status TINYINT(1),
        PRIMARY KEY (warehouse_id, id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_inventory
    (
        entry_id CHAR(2) NOT NULL,
        item_id CHAR(10) NOT NULL,
        actual_quantity DECIMAL(20,5),
        planing_out_quantity DECIMAL(20,5),
        planing_in_quantity DECIMAL(20,5),
        modify_by CHAR(32),
        modify_time DATETIME,
        PRIMARY KEY (entry_id, item_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_item
    (
        item_id CHAR(10) NOT NULL,
        item_group_id CHAR(15),
        name VARCHAR(60),
        unit CHAR(6),
        norm CHAR(10),
        rmrk CHAR(30),
        dec_no SMALLINT,
        image VARCHAR(200),
        create_by CHAR(32),
        create_time DATETIME,
        modify_by CHAR(32),
        modify_time DATETIME,
        status TINYINT,
        PRIMARY KEY (item_id),
        INDEX stock_item_idx1 (item_group_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_item_group
    (
        id CHAR(15) NOT NULL,
        name VARCHAR(60),
        is_group TINYINT(1),
        parent_id CHAR(15),
        status TINYINT,
        rmrk CHAR(30),
        icon_skin VARCHAR(50),
        PRIMARY KEY (id),
        INDEX idx_item_group_1 (parent_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_item_price
    (
        item_id CHAR(10) NOT NULL,
        price_id CHAR(4) NOT NULL,
        currency_id CHAR(3) NOT NULL,
        stand_price_rate DECIMAL(18,6),
        max_price_rate DECIMAL(18,6),
        min_price_rate DECIMAL(18,6),
        enabled TINYINT,
        PRIMARY KEY (item_id, price_id, currency_id),
        INDEX FK_stock_price_fk1 (price_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_price
    (
        id CHAR(4) NOT NULL,
        name VARCHAR(50),
        enabled TINYINT,
        currency CHAR(3),
        buying TINYINT,
        selling TINYINT,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_price_list
    (
        id INT(4) NOT NULL AUTO_INCREMENT,
        name VARCHAR(50),
        enabled TINYINT,
        currency CHAR(3),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    stock_warehouse
    (
        id CHAR(2) NOT NULL,
        name VARCHAR(120),
        phone_no CHAR(20),
        mobile_no CHAR(14),
        address VARCHAR(140),
        status CHAR(1),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_admin_role
    (
        role_id CHAR(32) NOT NULL,
        role_name VARCHAR(64),
        role_desc VARCHAR(200),
        role_type CHAR(32),
        PRIMARY KEY (role_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_admin_user
    (
        user_id CHAR(32) NOT NULL,
        active_status_flag CHAR(32),
        login_name VARCHAR(64),
        user_name VARCHAR(200),
        password CHAR(120),
        phone_number CHAR(20),
        email VARCHAR(50),
        role_name VARCHAR(64),
        create_date DATETIME,
        PRIMARY KEY (user_id),
        CONSTRAINT user_idx1 UNIQUE (login_name)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_menu
    (
        menu_id CHAR(32) NOT NULL,
        menu_name CHAR(30),
        action_url VARCHAR(256),
        sequence bigint,
        iconCls VARCHAR(100),
        is_leaf TINYINT(1),
        perm VARCHAR(200),
        PRIMARY KEY (menu_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_menu_xref
    (
        menu_id CHAR(32) NOT NULL,
        child_menu_id CHAR(32) NOT NULL,
        PRIMARY KEY (menu_id, child_menu_id),
        INDEX FK_ref_menu2 (child_menu_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_permission
    (
        PERMISSION_ID CHAR(32) NOT NULL,
        permission_desc VARCHAR(200),
        permission_NAME VARCHAR(200),
        status CHAR(1),
        PERMISSION_TYPE VARCHAR(240),
        is_friendly TINYINT(1),
        PRIMARY KEY (PERMISSION_ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_permission_xref
    (
        permission_id CHAR(32) NOT NULL,
        child_permission_id CHAR(32) NOT NULL,
        PRIMARY KEY (permission_id, child_permission_id),
        INDEX FK_ref_permission2 (child_permission_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_role_permission_xref
    (
        role_id CHAR(32) NOT NULL,
        permission_id CHAR(32) NOT NULL,
        PRIMARY KEY (role_id, permission_id),
        INDEX FK_ref_role_perm2 (permission_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_section
    (
        section_id CHAR(32) NOT NULL,
        ceiling_entity VARCHAR(255),
        display_controller VARCHAR(255),
        section_name VARCHAR(120),
        initUrl VARCHAR(300),
        parent_menu_id VARCHAR(2000),
        parent_section_id CHAR(32),
        perm VARCHAR(200),
        init_accessed TINYINT(1),
        PRIMARY KEY (section_id),
        INDEX idx_section (initUrl)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_sequence
    (
        id CHAR(4) NOT NULL,
        reset_cycle SMALLINT,
        reset_cycle_unit CHAR(1),
        LENGTH SMALLINT,
        current_sequence_no INT,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_user_permission_xref
    (
        user_id CHAR(32) NOT NULL,
        PERMISSION_ID CHAR(32) NOT NULL,
        PRIMARY KEY (user_id, PERMISSION_ID),
        INDEX FK_ref_user_perm2 (PERMISSION_ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
CREATE TABLE
    sys_user_role_xref
    (
        user_id CHAR(32) NOT NULL,
        role_id CHAR(32) NOT NULL,
        PRIMARY KEY (user_id, role_id),
        INDEX FK_ref_user_role2 (role_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
ALTER TABLE
    `act_ge_bytearray` ADD CONSTRAINT ACT_FK_BYTEARR_DEPL FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES
    `act_re_deployment` (`ID_`);
ALTER TABLE
    `act_id_membership` ADD CONSTRAINT ACT_FK_MEMB_GROUP FOREIGN KEY (`GROUP_ID_`) REFERENCES
    `act_id_group` (`ID_`) ;
ALTER TABLE
    `act_id_membership` ADD CONSTRAINT ACT_FK_MEMB_USER FOREIGN KEY (`USER_ID_`) REFERENCES
    `act_id_user` (`ID_`);
ALTER TABLE
    `act_procdef_info` ADD CONSTRAINT ACT_FK_INFO_JSON_BA FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES
    `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_procdef_info` ADD CONSTRAINT ACT_FK_INFO_PROCDEF FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES
    `act_re_procdef` (`ID_`);
ALTER TABLE
    `act_re_model` ADD CONSTRAINT ACT_FK_MODEL_DEPLOYMENT FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES
    `act_re_deployment` (`ID_`) ;
ALTER TABLE
    `act_re_model` ADD CONSTRAINT ACT_FK_MODEL_SOURCE FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`)
    REFERENCES `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_re_model` ADD CONSTRAINT ACT_FK_MODEL_SOURCE_EXTRA FOREIGN KEY
    (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`);
ALTER TABLE
    `act_ru_deadletter_job` ADD CONSTRAINT ACT_FK_DEADLETTER_JOB_EXCEPTION FOREIGN KEY
    (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_ru_deadletter_job` ADD CONSTRAINT ACT_FK_DEADLETTER_JOB_EXECUTION FOREIGN KEY
    (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_deadletter_job` ADD CONSTRAINT ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE FOREIGN KEY
    (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_deadletter_job` ADD CONSTRAINT ACT_FK_DEADLETTER_JOB_PROC_DEF FOREIGN KEY
    (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`);
ALTER TABLE
    `act_ru_event_subscr` ADD CONSTRAINT ACT_FK_EVENT_EXEC FOREIGN KEY (`EXECUTION_ID_`) REFERENCES
    `act_ru_execution` (`ID_`);
ALTER TABLE
    `act_ru_execution` ADD CONSTRAINT ACT_FK_EXE_PARENT FOREIGN KEY (`PARENT_ID_`) REFERENCES
    `act_ru_execution` (`ID_`)
ON
DELETE
    CASCADE;
ALTER TABLE
    `act_ru_execution` ADD CONSTRAINT ACT_FK_EXE_PROCDEF FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES
    `act_re_procdef` (`ID_`) ;
ALTER TABLE
    `act_ru_execution` ADD CONSTRAINT ACT_FK_EXE_PROCINST FOREIGN KEY (`PROC_INST_ID_`) REFERENCES
    `act_ru_execution` (`ID_`)
ON
DELETE
    CASCADE
ON
UPDATE
    CASCADE;
ALTER TABLE
    `act_ru_execution` ADD CONSTRAINT ACT_FK_EXE_SUPER FOREIGN KEY (`SUPER_EXEC_`) REFERENCES
    `act_ru_execution` (`ID_`)
ON
DELETE
    CASCADE;
ALTER TABLE
    `act_ru_identitylink` ADD CONSTRAINT ACT_FK_ATHRZ_PROCEDEF FOREIGN KEY (`PROC_DEF_ID_`)
    REFERENCES `act_re_procdef` (`ID_`) ;
ALTER TABLE
    `act_ru_identitylink` ADD CONSTRAINT ACT_FK_IDL_PROCINST FOREIGN KEY (`PROC_INST_ID_`)
    REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_identitylink` ADD CONSTRAINT ACT_FK_TSKASS_TASK FOREIGN KEY (`TASK_ID_`) REFERENCES
    `act_ru_task` (`ID_`);
ALTER TABLE
    `act_ru_job` ADD CONSTRAINT ACT_FK_JOB_EXCEPTION FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES
    `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_ru_job` ADD CONSTRAINT ACT_FK_JOB_EXECUTION FOREIGN KEY (`EXECUTION_ID_`) REFERENCES
    `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_job` ADD CONSTRAINT ACT_FK_JOB_PROCESS_INSTANCE FOREIGN KEY (`PROCESS_INSTANCE_ID_`)
    REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_job` ADD CONSTRAINT ACT_FK_JOB_PROC_DEF FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES
    `act_re_procdef` (`ID_`);
ALTER TABLE
    `act_ru_suspended_job` ADD CONSTRAINT ACT_FK_SUSPENDED_JOB_EXCEPTION FOREIGN KEY
    (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_ru_suspended_job` ADD CONSTRAINT ACT_FK_SUSPENDED_JOB_EXECUTION FOREIGN KEY
    (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_suspended_job` ADD CONSTRAINT ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE FOREIGN KEY
    (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_suspended_job` ADD CONSTRAINT ACT_FK_SUSPENDED_JOB_PROC_DEF FOREIGN KEY (`PROC_DEF_ID_`
    ) REFERENCES `act_re_procdef` (`ID_`);
ALTER TABLE
    `act_ru_task` ADD CONSTRAINT ACT_FK_TASK_EXE FOREIGN KEY (`EXECUTION_ID_`) REFERENCES
    `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_task` ADD CONSTRAINT ACT_FK_TASK_PROCDEF FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES
    `act_re_procdef` (`ID_`) ;
ALTER TABLE
    `act_ru_task` ADD CONSTRAINT ACT_FK_TASK_PROCINST FOREIGN KEY (`PROC_INST_ID_`) REFERENCES
    `act_ru_execution` (`ID_`);
ALTER TABLE
    `act_ru_timer_job` ADD CONSTRAINT ACT_FK_TIMER_JOB_EXCEPTION FOREIGN KEY (`EXCEPTION_STACK_ID_`
    ) REFERENCES `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_ru_timer_job` ADD CONSTRAINT ACT_FK_TIMER_JOB_EXECUTION FOREIGN KEY (`EXECUTION_ID_`)
    REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_timer_job` ADD CONSTRAINT ACT_FK_TIMER_JOB_PROCESS_INSTANCE FOREIGN KEY
    (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_timer_job` ADD CONSTRAINT ACT_FK_TIMER_JOB_PROC_DEF FOREIGN KEY (`PROC_DEF_ID_`)
    REFERENCES `act_re_procdef` (`ID_`);
ALTER TABLE
    `act_ru_variable` ADD CONSTRAINT ACT_FK_VAR_BYTEARRAY FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES
    `act_ge_bytearray` (`ID_`) ;
ALTER TABLE
    `act_ru_variable` ADD CONSTRAINT ACT_FK_VAR_EXE FOREIGN KEY (`EXECUTION_ID_`) REFERENCES
    `act_ru_execution` (`ID_`) ;
ALTER TABLE
    `act_ru_variable` ADD CONSTRAINT ACT_FK_VAR_PROCINST FOREIGN KEY (`PROC_INST_ID_`) REFERENCES
    `act_ru_execution` (`ID_`);
ALTER TABLE
    `common_department_user_xref` ADD CONSTRAINT FK_fk_common_department_user1 FOREIGN KEY
    (`department_id`) REFERENCES `common_department` (`id`);
ALTER TABLE
    `common_dict` ADD CONSTRAINT FK_common_dict_fk1 FOREIGN KEY (`dict_type`) REFERENCES
    `common_dict_type` (`id`)
ON
DELETE
    CASCADE;
ALTER TABLE
    `purchase_order_detail` ADD CONSTRAINT FK_purchase_order_detail_fk1 FOREIGN KEY (`order_id`)
    REFERENCES `purchase_order` (`order_id`);
ALTER TABLE
    `stock_entry` ADD CONSTRAINT FK_stock_sub_warehouse_fk1 FOREIGN KEY (`warehouse_id`) REFERENCES
    `stock_warehouse` (`id`)
ON
DELETE
    CASCADE;
ALTER TABLE
    `stock_item_price` ADD CONSTRAINT FK_stock_item_fk1 FOREIGN KEY (`item_id`) REFERENCES
    `stock_item` (`item_id`) ;
ALTER TABLE
    `stock_item_price` ADD CONSTRAINT FK_stock_price_fk1 FOREIGN KEY (`price_id`) REFERENCES
    `stock_price` (`id`);
ALTER TABLE
    `sys_menu_xref` ADD CONSTRAINT FK_ref_menu1 FOREIGN KEY (`menu_id`) REFERENCES `sys_menu`
    (`menu_id`) ;
ALTER TABLE
    `sys_menu_xref` ADD CONSTRAINT FK_ref_menu2 FOREIGN KEY (`child_menu_id`) REFERENCES `sys_menu`
    (`menu_id`);
ALTER TABLE
    `sys_permission_xref` ADD CONSTRAINT FK_ref_permission1 FOREIGN KEY (`permission_id`)
    REFERENCES `sys_permission` (`PERMISSION_ID`) ;
ALTER TABLE
    `sys_permission_xref` ADD CONSTRAINT FK_ref_permission2 FOREIGN KEY (`child_permission_id`)
    REFERENCES `sys_permission` (`PERMISSION_ID`);
ALTER TABLE
    `sys_role_permission_xref` ADD CONSTRAINT FK_ref_role_perm1 FOREIGN KEY (`role_id`) REFERENCES
    `sys_admin_role` (`role_id`) ;
ALTER TABLE
    `sys_role_permission_xref` ADD CONSTRAINT FK_ref_role_perm2 FOREIGN KEY (`permission_id`)
    REFERENCES `sys_permission` (`PERMISSION_ID`);
ALTER TABLE
    `sys_user_permission_xref` ADD CONSTRAINT FK_ref_user_perm1 FOREIGN KEY (`user_id`) REFERENCES
    `sys_admin_user` (`user_id`) ;
ALTER TABLE
    `sys_user_permission_xref` ADD CONSTRAINT FK_ref_user_perm2 FOREIGN KEY (`PERMISSION_ID`)
    REFERENCES `sys_permission` (`PERMISSION_ID`);
ALTER TABLE
    `sys_user_role_xref` ADD CONSTRAINT FK_ref_user_role1 FOREIGN KEY (`user_id`) REFERENCES
    `sys_admin_user` (`user_id`) ;
ALTER TABLE
    `sys_user_role_xref` ADD CONSTRAINT FK_ref_user_role2 FOREIGN KEY (`role_id`) REFERENCES
    `sys_admin_role` (`role_id`);
