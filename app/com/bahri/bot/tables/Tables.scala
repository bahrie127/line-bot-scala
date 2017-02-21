package tables
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Configs.schema, TableAnswers.schema, TableMemories.schema, TableOrderProducts.schema, TableOrders.schema, TableProducts.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Configs
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param key Database column key SqlType(VARCHAR), Length(20,true), Default(None)
   *  @param value Database column value SqlType(VARCHAR), Length(20,true), Default(None) */
  case class ConfigsRow(id: Int, key: Option[String] = None, value: Option[String] = None)
  /** GetResult implicit for fetching ConfigsRow objects using plain SQL queries */
  implicit def GetResultConfigsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ConfigsRow] = GR{
    prs => import prs._
    ConfigsRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table configs. Objects of this class serve as prototypes for rows in queries. */
  class Configs(_tableTag: Tag) extends Table[ConfigsRow](_tableTag, "configs") {
    def * = (id, key, value) <> (ConfigsRow.tupled, ConfigsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), key, value).shaped.<>({r=>import r._; _1.map(_=> ConfigsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column key SqlType(VARCHAR), Length(20,true), Default(None) */
    val key: Rep[Option[String]] = column[Option[String]]("key", O.Length(20,varying=true), O.Default(None))
    /** Database column value SqlType(VARCHAR), Length(20,true), Default(None) */
    val value: Rep[Option[String]] = column[Option[String]]("value", O.Length(20,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Configs */
  lazy val Configs = new TableQuery(tag => new Configs(tag))

  /** Entity class storing rows of table TableAnswers
   *  @param id Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true), Default()
   *  @param answerNo Database column answer_no SqlType(INT)
   *  @param answerText Database column answer_text SqlType(VARCHAR), Length(255,true), Default()
   *  @param dateCreated Database column date_created SqlType(TIMESTAMP) */
  case class TableAnswersRow(id: Long, userId: String = "", answerNo: Int, answerText: String = "", dateCreated: java.sql.Timestamp)
  /** GetResult implicit for fetching TableAnswersRow objects using plain SQL queries */
  implicit def GetResultTableAnswersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp]): GR[TableAnswersRow] = GR{
    prs => import prs._
    TableAnswersRow.tupled((<<[Long], <<[String], <<[Int], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table table_answers. Objects of this class serve as prototypes for rows in queries. */
  class TableAnswers(_tableTag: Tag) extends Table[TableAnswersRow](_tableTag, "table_answers") {
    def * = (id, userId, answerNo, answerText, dateCreated) <> (TableAnswersRow.tupled, TableAnswersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(answerNo), Rep.Some(answerText), Rep.Some(dateCreated)).shaped.<>({r=>import r._; _1.map(_=> TableAnswersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true), Default() */
    val userId: Rep[String] = column[String]("user_id", O.Length(50,varying=true), O.Default(""))
    /** Database column answer_no SqlType(INT) */
    val answerNo: Rep[Int] = column[Int]("answer_no")
    /** Database column answer_text SqlType(VARCHAR), Length(255,true), Default() */
    val answerText: Rep[String] = column[String]("answer_text", O.Length(255,varying=true), O.Default(""))
    /** Database column date_created SqlType(TIMESTAMP) */
    val dateCreated: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
  }
  /** Collection-like TableQuery object for table TableAnswers */
  lazy val TableAnswers = new TableQuery(tag => new TableAnswers(tag))

  /** Entity class storing rows of table TableMemories
   *  @param id Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param typeEvent Database column type_event SqlType(VARCHAR), Length(20,true), Default()
   *  @param typeSource Database column type_source SqlType(VARCHAR), Length(20,true), Default()
   *  @param typeMessage Database column type_message SqlType(VARCHAR), Length(20,true), Default()
   *  @param replyToken Database column reply_token SqlType(VARCHAR), Length(50,true), Default()
   *  @param chatTime Database column chat_time SqlType(TIMESTAMP)
   *  @param text Database column text SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param messageId Database column message_id SqlType(VARCHAR), Length(50,true), Default()
   *  @param stickerId Database column sticker_id SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param packageId Database column package_id SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param title Database column title SqlType(VARCHAR), Length(50,true), Default(Some())
   *  @param address Database column address SqlType(TEXT), Default(None)
   *  @param latitude Database column latitude SqlType(DECIMAL), Default(None)
   *  @param longitude Database column longitude SqlType(DECIMAL), Default(None)
   *  @param roomId Database column room_id SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param groupId Database column group_id SqlType(VARCHAR), Length(50,true), Default(None) */
  case class TableMemoriesRow(id: Long, userId: Option[String] = None, typeEvent: String = "", typeSource: String = "", typeMessage: String = "", replyToken: String = "", chatTime: java.sql.Timestamp, text: Option[String] = None, messageId: String = "", stickerId: Option[String] = None, packageId: Option[String] = None, title: Option[String] = Some(""), address: Option[String] = None, latitude: Option[scala.math.BigDecimal] = None, longitude: Option[scala.math.BigDecimal] = None, roomId: Option[String] = None, groupId: Option[String] = None)
  /** GetResult implicit for fetching TableMemoriesRow objects using plain SQL queries */
  implicit def GetResultTableMemoriesRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[String], e3: GR[java.sql.Timestamp], e4: GR[Option[scala.math.BigDecimal]]): GR[TableMemoriesRow] = GR{
    prs => import prs._
    TableMemoriesRow.tupled((<<[Long], <<?[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal], <<?[String], <<?[String]))
  }
  /** Table description of table table_memories. Objects of this class serve as prototypes for rows in queries. */
  class TableMemories(_tableTag: Tag) extends Table[TableMemoriesRow](_tableTag, "table_memories") {
    def * = (id, userId, typeEvent, typeSource, typeMessage, replyToken, chatTime, text, messageId, stickerId, packageId, title, address, latitude, longitude, roomId, groupId) <> (TableMemoriesRow.tupled, TableMemoriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), userId, Rep.Some(typeEvent), Rep.Some(typeSource), Rep.Some(typeMessage), Rep.Some(replyToken), Rep.Some(chatTime), text, Rep.Some(messageId), stickerId, packageId, title, address, latitude, longitude, roomId, groupId).shaped.<>({r=>import r._; _1.map(_=> TableMemoriesRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9.get, _10, _11, _12, _13, _14, _15, _16, _17)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val userId: Rep[Option[String]] = column[Option[String]]("user_id", O.Length(50,varying=true), O.Default(None))
    /** Database column type_event SqlType(VARCHAR), Length(20,true), Default() */
    val typeEvent: Rep[String] = column[String]("type_event", O.Length(20,varying=true), O.Default(""))
    /** Database column type_source SqlType(VARCHAR), Length(20,true), Default() */
    val typeSource: Rep[String] = column[String]("type_source", O.Length(20,varying=true), O.Default(""))
    /** Database column type_message SqlType(VARCHAR), Length(20,true), Default() */
    val typeMessage: Rep[String] = column[String]("type_message", O.Length(20,varying=true), O.Default(""))
    /** Database column reply_token SqlType(VARCHAR), Length(50,true), Default() */
    val replyToken: Rep[String] = column[String]("reply_token", O.Length(50,varying=true), O.Default(""))
    /** Database column chat_time SqlType(TIMESTAMP) */
    val chatTime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("chat_time")
    /** Database column text SqlType(VARCHAR), Length(255,true), Default(None) */
    val text: Rep[Option[String]] = column[Option[String]]("text", O.Length(255,varying=true), O.Default(None))
    /** Database column message_id SqlType(VARCHAR), Length(50,true), Default() */
    val messageId: Rep[String] = column[String]("message_id", O.Length(50,varying=true), O.Default(""))
    /** Database column sticker_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val stickerId: Rep[Option[String]] = column[Option[String]]("sticker_id", O.Length(50,varying=true), O.Default(None))
    /** Database column package_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val packageId: Rep[Option[String]] = column[Option[String]]("package_id", O.Length(50,varying=true), O.Default(None))
    /** Database column title SqlType(VARCHAR), Length(50,true), Default(Some()) */
    val title: Rep[Option[String]] = column[Option[String]]("title", O.Length(50,varying=true), O.Default(Some("")))
    /** Database column address SqlType(TEXT), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Default(None))
    /** Database column latitude SqlType(DECIMAL), Default(None) */
    val latitude: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("latitude", O.Default(None))
    /** Database column longitude SqlType(DECIMAL), Default(None) */
    val longitude: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("longitude", O.Default(None))
    /** Database column room_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val roomId: Rep[Option[String]] = column[Option[String]]("room_id", O.Length(50,varying=true), O.Default(None))
    /** Database column group_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val groupId: Rep[Option[String]] = column[Option[String]]("group_id", O.Length(50,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table TableMemories */
  lazy val TableMemories = new TableQuery(tag => new TableMemories(tag))

  /** Entity class storing rows of table TableOrderProducts
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param productId Database column product_id SqlType(INT), Default(None)
   *  @param orderD Database column order_d SqlType(INT), Default(None)
   *  @param quantity Database column quantity SqlType(INT), Default(None) */
  case class TableOrderProductsRow(id: Int, productId: Option[Int] = None, orderD: Option[Int] = None, quantity: Option[Int] = None)
  /** GetResult implicit for fetching TableOrderProductsRow objects using plain SQL queries */
  implicit def GetResultTableOrderProductsRow(implicit e0: GR[Int], e1: GR[Option[Int]]): GR[TableOrderProductsRow] = GR{
    prs => import prs._
    TableOrderProductsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table table_order_products. Objects of this class serve as prototypes for rows in queries. */
  class TableOrderProducts(_tableTag: Tag) extends Table[TableOrderProductsRow](_tableTag, "table_order_products") {
    def * = (id, productId, orderD, quantity) <> (TableOrderProductsRow.tupled, TableOrderProductsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), productId, orderD, quantity).shaped.<>({r=>import r._; _1.map(_=> TableOrderProductsRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column product_id SqlType(INT), Default(None) */
    val productId: Rep[Option[Int]] = column[Option[Int]]("product_id", O.Default(None))
    /** Database column order_d SqlType(INT), Default(None) */
    val orderD: Rep[Option[Int]] = column[Option[Int]]("order_d", O.Default(None))
    /** Database column quantity SqlType(INT), Default(None) */
    val quantity: Rep[Option[Int]] = column[Option[Int]]("quantity", O.Default(None))
  }
  /** Collection-like TableQuery object for table TableOrderProducts */
  lazy val TableOrderProducts = new TableQuery(tag => new TableOrderProducts(tag))

  /** Entity class storing rows of table TableOrders
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true), Default()
   *  @param totalPrice Database column total_price SqlType(INT), Default(0)
   *  @param shippingPrice Database column shipping_price SqlType(INT), Default(0)
   *  @param totalPayment Database column total_payment SqlType(INT), Default(0)
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param phone Database column phone SqlType(VARCHAR), Length(20,true), Default()
   *  @param email Database column email SqlType(VARCHAR), Length(100,true), Default()
   *  @param address Database column address SqlType(TEXT)
   *  @param status Database column status SqlType(INT), Default(0)
   *  @param orderCreated Database column order_created SqlType(TIMESTAMP)
   *  @param orderUpdated Database column order_updated SqlType(TIMESTAMP) */
  case class TableOrdersRow(id: Int, userId: String = "", totalPrice: Int = 0, shippingPrice: Int = 0, totalPayment: Int = 0, name: String, phone: String = "", email: String = "", address: String, status: Int = 0, orderCreated: Option[java.sql.Timestamp], orderUpdated: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching TableOrdersRow objects using plain SQL queries */
  implicit def GetResultTableOrdersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[TableOrdersRow] = GR{
    prs => import prs._
    TableOrdersRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table table_orders. Objects of this class serve as prototypes for rows in queries. */
  class TableOrders(_tableTag: Tag) extends Table[TableOrdersRow](_tableTag, "table_orders") {
    def * = (id, userId, totalPrice, shippingPrice, totalPayment, name, phone, email, address, status, orderCreated, orderUpdated) <> (TableOrdersRow.tupled, TableOrdersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(totalPrice), Rep.Some(shippingPrice), Rep.Some(totalPayment), Rep.Some(name), Rep.Some(phone), Rep.Some(email), Rep.Some(address), Rep.Some(status), orderCreated, orderUpdated).shaped.<>({r=>import r._; _1.map(_=> TableOrdersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true), Default() */
    val userId: Rep[String] = column[String]("user_id", O.Length(50,varying=true), O.Default(""))
    /** Database column total_price SqlType(INT), Default(0) */
    val totalPrice: Rep[Int] = column[Int]("total_price", O.Default(0))
    /** Database column shipping_price SqlType(INT), Default(0) */
    val shippingPrice: Rep[Int] = column[Int]("shipping_price", O.Default(0))
    /** Database column total_payment SqlType(INT), Default(0) */
    val totalPayment: Rep[Int] = column[Int]("total_payment", O.Default(0))
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column phone SqlType(VARCHAR), Length(20,true), Default() */
    val phone: Rep[String] = column[String]("phone", O.Length(20,varying=true), O.Default(""))
    /** Database column email SqlType(VARCHAR), Length(100,true), Default() */
    val email: Rep[String] = column[String]("email", O.Length(100,varying=true), O.Default(""))
    /** Database column address SqlType(TEXT) */
    val address: Rep[String] = column[String]("address")
    /** Database column status SqlType(INT), Default(0) */
    val status: Rep[Int] = column[Int]("status", O.Default(0))
    /** Database column order_created SqlType(TIMESTAMP) */
    val orderCreated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("order_created")
    /** Database column order_updated SqlType(TIMESTAMP) */
    val orderUpdated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("order_updated")
  }
  /** Collection-like TableQuery object for table TableOrders */
  lazy val TableOrders = new TableQuery(tag => new TableOrders(tag))

  /** Entity class storing rows of table TableProducts
   *  @param id Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey
   *  @param codeProduct Database column code_product SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param nameProduct Database column name_product SqlType(VARCHAR), Length(100,true), Default(None)
   *  @param imgUrl1 Database column img_url_1 SqlType(VARCHAR), Length(200,true), Default(None)
   *  @param imgUrl2 Database column img_url_2 SqlType(VARCHAR), Length(200,true), Default(None)
   *  @param price Database column price SqlType(INT), Default(None)
   *  @param stock Database column stock SqlType(INT), Default(None) */
  case class TableProductsRow(id: Long, codeProduct: Option[String] = None, nameProduct: Option[String] = None, imgUrl1: Option[String] = None, imgUrl2: Option[String] = None, price: Option[Int] = None, stock: Option[Int] = None)
  /** GetResult implicit for fetching TableProductsRow objects using plain SQL queries */
  implicit def GetResultTableProductsRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[TableProductsRow] = GR{
    prs => import prs._
    TableProductsRow.tupled((<<[Long], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[Int]))
  }
  /** Table description of table table_products. Objects of this class serve as prototypes for rows in queries. */
  class TableProducts(_tableTag: Tag) extends Table[TableProductsRow](_tableTag, "table_products") {
    def * = (id, codeProduct, nameProduct, imgUrl1, imgUrl2, price, stock) <> (TableProductsRow.tupled, TableProductsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), codeProduct, nameProduct, imgUrl1, imgUrl2, price, stock).shaped.<>({r=>import r._; _1.map(_=> TableProductsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column code_product SqlType(VARCHAR), Length(50,true), Default(None) */
    val codeProduct: Rep[Option[String]] = column[Option[String]]("code_product", O.Length(50,varying=true), O.Default(None))
    /** Database column name_product SqlType(VARCHAR), Length(100,true), Default(None) */
    val nameProduct: Rep[Option[String]] = column[Option[String]]("name_product", O.Length(100,varying=true), O.Default(None))
    /** Database column img_url_1 SqlType(VARCHAR), Length(200,true), Default(None) */
    val imgUrl1: Rep[Option[String]] = column[Option[String]]("img_url_1", O.Length(200,varying=true), O.Default(None))
    /** Database column img_url_2 SqlType(VARCHAR), Length(200,true), Default(None) */
    val imgUrl2: Rep[Option[String]] = column[Option[String]]("img_url_2", O.Length(200,varying=true), O.Default(None))
    /** Database column price SqlType(INT), Default(None) */
    val price: Rep[Option[Int]] = column[Option[Int]]("price", O.Default(None))
    /** Database column stock SqlType(INT), Default(None) */
    val stock: Rep[Option[Int]] = column[Option[Int]]("stock", O.Default(None))
  }
  /** Collection-like TableQuery object for table TableProducts */
  lazy val TableProducts = new TableQuery(tag => new TableProducts(tag))
}
