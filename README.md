
    <bean id="userRepository" class="com.cardpay.security.components.InMemoryUserRepository">
        <constructor-arg name="users">
            <map>
                <entry key="user" value="password"/>
            </map>
        </constructor-arg>
        <constructor-arg name="roles">
            <map>
                <entry key="user" value="ROLE_USER"/>
            </map>
        </constructor-arg>
    </bean>
