
@template.layout.page( content = @`
      <h1>Анализатор страниц</h1>
            <p>Бесплатно проверяйте сайты на SEO пригодность</p>

            <form>
            <p><input type="text" name="url" placeholder="Адрес сайта" size="18" /></p>
            <p>
            <button type="submit" formmethod="post" formaction="/urls">Проверить</button>
            <button type="reset">Отмена</button>
            </p>
            </form>
    `
)
