Entity@Entity
@Table(name = "na_account_role")

    @Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.newaim.core.jpa.IdGenerator",
		parameters = {@Parameter(name = "prefix", value = "ROLE")})