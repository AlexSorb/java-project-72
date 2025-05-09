@import hexlet.code.dto.url.UrlPage

@param UrlPage page

@template.layout.page(
    page = page,
    content = @`
        <div class="table">
            <h1>Сайт: ${page.getUrl().getName()}</h1>
            <table>
              <tbody>
                <tr>
                  <th>ID</th>
                  <th> ${page.getUrl().getId()}</th>
                </tr>
                <tr>
                  <th>Имя</th>
                  <th> ${page.getUrl().getName()}</th>
                </tr>
                <tr>
                  <th>Дата создания</th>
                  <th> ${page.getUrl().getCreatedAt().toString()}</th>
                </tr>
              </tbody>
            </table>
        <div>

        <div>
              <form>
                    <p> ${page.getUrl().getId()} : ${page.getUrl().getName()}</p>
                    <button type="submit" formmethod="post" formaction="/urls/${page.getUrl().getId()}/checks">Запустить проверку</button>
              </form>
        </div>
        `
)