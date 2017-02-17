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
  lazy val schema: profile.SchemaDescription = Configs.schema ++ TableMemories.schema ++ TableOrderProducts.schema ++ TableOrders.schema ++ TableProducts.schema
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

  /** Entity class storing rows of table TableMemories
   *  @param id Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true), Default()
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
   *  @param longitude Database column longitude SqlType(DECIMAL), Default(None) */
  case class TableMemoriesRow(id: Long, userId: String = "", typeEvent: String = "", typeSource: String = "", typeMessage: String = "", replyToken: String = "", chatTime: java.sql.Timestamp, text: Option[String] = None, messageId: String = "", stickerId: Option[String] = None, packageId: Option[String] = None, title: Option[String] = Some(""), address: Option[String] = None, latitude: Option[scala.math.BigDecimal] = None, longitude: Option[scala.math.BigDecimal] = None)
  /** GetResult implicit for fetching TableMemoriesRow objects using plain SQL queries */
  implicit def GetResultTableMemoriesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]], e4: GR[Option[scala.math.BigDecimal]]): GR[TableMemoriesRow] = GR{
    prs => import prs._
    TableMemoriesRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[scala.math.BigDecimal], <<?[scala.math.BigDecimal]))
  }
  /** Table description of table table_memories. Objects of this class serve as prototypes for rows in queries. */
  class TableMemories(_tableTag: Tag) extends Table[TableMemoriesRow](_tableTag, "table_memories") {
    def * = (id, userId, typeEvent, typeSource, typeMessage, replyToken, chatTime, text, messageId, stickerId, packageId, title, address, latitude, longitude) <> (TableMemoriesRow.tupled, TableMemoriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(typeEvent), Rep.Some(typeSource), Rep.Some(typeMessage), Rep.Some(replyToken), Rep.Some(chatTime), text, Rep.Some(messageId), stickerId, packageId, title, address, latitude, longitude).shaped.<>({r=>import r._; _1.map(_=> TableMemoriesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9.get, _10, _11, _12, _13, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true), Default() */
    val userId: Rep[String] = column[String]("user_id", O.Length(50,varying=true), O.Default(""))
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
   *  @param userId Database column user_id SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param totalPrice Database column total_price SqlType(INT), Default(None)
   *  @param shippingPrice Database column shipping_price SqlType(INT), Default(None)
   *  @param totalPayment Database column total_payment SqlType(INT), Default(None)
   *  @param email Database column email SqlType(VARCHAR), Length(100,true), Default(None)
   *  @param address Database column address SqlType(TEXT), Default(None)
   *  @param status Database column status SqlType(INT), Default(None)
   *  @param orderCreated Database column order_created SqlType(TIMESTAMP)
   *  @param orderUpdated Database column order_updated SqlType(TIMESTAMP) */
  case class TableOrdersRow(id: Int, userId: Option[String] = None, totalPrice: Option[Int] = None, shippingPrice: Option[Int] = None, totalPayment: Option[Int] = None, email: Option[String] = None, address: Option[String] = None, status: Option[Int] = None, orderCreated: Option[java.sql.Timestamp], orderUpdated: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching TableOrdersRow objects using plain SQL queries */
  implicit def GetResultTableOrdersRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[java.sql.Timestamp]]): GR[TableOrdersRow] = GR{
    prs => import prs._
    TableOrdersRow.tupled((<<[Int], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table table_orders. Objects of this class serve as prototypes for rows in queries. */
  class TableOrders(_tableTag: Tag) extends Table[TableOrdersRow](_tableTag, "table_orders") {
    def * = (id, userId, totalPrice, shippingPrice, totalPayment, email, address, status, orderCreated, orderUpdated) <> (TableOrdersRow.tupled, TableOrdersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), userId, totalPrice, shippingPrice, totalPayment, email, address, status, orderCreated, orderUpdated).shaped.<>({r=>import r._; _1.map(_=> TableOrdersRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(VARCHAR), Length(50,true), Default(None) */
    val userId: Rep[Option[String]] = column[Option[String]]("user_id", O.Length(50,varying=true), O.Default(None))
    /** Database column total_price SqlType(INT), Default(None) */
    val totalPrice: Rep[Option[Int]] = column[Option[Int]]("total_price", O.Default(None))
    /** Database column shipping_price SqlType(INT), Default(None) */
    val shippingPrice: Rep[Option[Int]] = column[Option[Int]]("shipping_price", O.Default(None))
    /** Database column total_payment SqlType(INT), Default(None) */
    val totalPayment: Rep[Option[Int]] = column[Option[Int]]("total_payment", O.Default(None))
    /** Database column email SqlType(VARCHAR), Length(100,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(100,varying=true), O.Default(None))
    /** Database column address SqlType(TEXT), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Default(None))
    /** Database column status SqlType(INT), Default(None) */
    val status: Rep[Option[Int]] = column[Option[Int]]("status", O.Default(None))
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
